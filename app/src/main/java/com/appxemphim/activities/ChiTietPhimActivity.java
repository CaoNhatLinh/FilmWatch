package com.appxemphim.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appxemphim.R;
import com.appxemphim.dao.PhimDAO;
import com.appxemphim.data.DanhGia;
import com.appxemphim.data.Phim;
import com.appxemphim.data.TheLoai;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

public class ChiTietPhimActivity extends AppCompatActivity {
    private ImageView posterImageView;
    private TextView titleTextView, genreTextView, ratingTextView, descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phim);

        // Ánh xạ view từ layout
        titleTextView = findViewById(R.id.movieTitle);
        descriptionTextView = findViewById(R.id.movieDescription);
        posterImageView = findViewById(R.id.moviePoster);
        genreTextView = findViewById(R.id.movieGenre);
        ratingTextView = findViewById(R.id.movieRating);
        int maPhim = getIntent().getIntExtra("MaPhim", -1);
        if (maPhim != -1) {
            fetchPhimDetails(maPhim);
        } else {
            Toast.makeText(this, "Không tìm thấy mã phim", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        back();
    }
    public void back()
    {
        ImageView back = findViewById(R.id.ivBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void fetchPhimDetails(int maPhim) {
        PhimDAO phimDAO = new PhimDAO();
        phimDAO.getPhimById(maPhim, new PhimDAO.PhimByIdCallback() {
            @Override
            public void onSuccess(Phim phim) {
                titleTextView.setText(phim.getTieuDe());
                descriptionTextView.setText(phim.getMoTa());
                Glide.with(ChiTietPhimActivity.this).load(phim.getAnhBia()).into(posterImageView);

                fetchTheLoaiPhim(phim.getMaPhim());
                fetchDanhGiaPhim(phim.getMaPhim());
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(ChiTietPhimActivity.this, "Không thể lấy thông tin phim", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchTheLoaiPhim(int maPhim) {
        PhimDAO phimDAO = new PhimDAO();
        phimDAO.getTheLoaiPhim(maPhim, new PhimDAO.TheLoaiCallback() {
            @Override
            public void onSuccess(List<TheLoai> theLoaiList) {
                if (!theLoaiList.isEmpty()) {
                    StringBuilder genres = new StringBuilder();
                    for (TheLoai theLoai : theLoaiList) {
                        if (genres.length() > 0) {
                            genres.append(", ");
                        }
                        genres.append(theLoai.getTenTheLoai());
                    }
                    genreTextView.setText(genres.toString());
                }
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(ChiTietPhimActivity.this, "Không thể lấy thể loại phim", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void fetchDanhGiaPhim(int maPhim) {
        PhimDAO phimDAO = new PhimDAO();
        phimDAO.getDanhGiaPhim(maPhim, new PhimDAO.DanhGiaCallback() {
            @Override
            public void onSuccess(List<DanhGia> danhGiaList) {
                if (!danhGiaList.isEmpty()) {
                    double averageRating = 0;
                    for (DanhGia danhGia : danhGiaList) {
                        averageRating += danhGia.getDanhGia();
                    }
                    averageRating /= danhGiaList.size();
                    ratingTextView.setText("Đánh giá: "+ String.valueOf(averageRating)+"/10");
                } else {
                    ratingTextView.setText("Chưa có đánh giá");
                }
            }

            @Override
            public void onFailure(String error) {
//                Toast.makeText(ChiTietPhimActivity.this, "Không thể lấy đánh giá phim", Toast.LENGTH_SHORT).show();
                Toast.makeText(ChiTietPhimActivity.this, "Lỗi: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
