package com.appxemphim.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
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
    private TextView titleTextView, genreTextView, ratingTextView, descriptionTextView, tvReadMore;
    private boolean isExpanded = false;
    private String initialDescription;
    private RatingBar movieRatingBar;

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
        movieRatingBar = findViewById(R.id.movieRatingBar);

        int maPhim = getIntent().getIntExtra("MaPhim", -1);
        if (maPhim != -1) {
            fetchPhimDetails(maPhim);
        } else {
            Toast.makeText(this, "Không tìm thấy mã phim", Toast.LENGTH_SHORT).show();
        }

        tvReadMore = findViewById(R.id.tvReadMore);
        ScrollView scrollView = findViewById(R.id.scrollView);
        initialDescription = descriptionTextView.getText().toString();
        if (initialDescription.length() > 5) {
            tvReadMore.setVisibility(View.VISIBLE);

            tvReadMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Nếu mô tả đã mở rộng, thu gọn lại
                    if (isExpanded) {
                        // Thu gọn mô tả
                        descriptionTextView.setText(initialDescription);
                        descriptionTextView.setMaxLines(5);
                        tvReadMore.setText("Xem thêm");
                        isExpanded = false;
                    } else {
                        descriptionTextView.setMaxLines(Integer.MAX_VALUE);
                        tvReadMore.setText("Thu gọn");
                        isExpanded = true;
                    }
                    initialDescription = descriptionTextView.getText().toString();
                    scrollView.fullScroll(View.FOCUS_DOWN);
                }
            });
        }

        movieRatingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (fromUser) {
                int maNguoiDung = getCurrentUserID();
                if (maNguoiDung != -1) {
                    checkAndSendRatingToServer(maPhim, rating);
                }
            }
        });
    }

    private int getCurrentUserID() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getInt("userId", -1);
    }

    private void checkAndSendRatingToServer(int maPhim, float rating) {
        int maNguoiDung = getCurrentUserID();
        if (maNguoiDung == -1) {
            Toast.makeText(this, "Không thể lấy mã người dùng. Vui lòng đăng nhập lại.", Toast.LENGTH_SHORT).show();
            return;
        }

        PhimDAO phimDAO = new PhimDAO();
        phimDAO.getDanhGiaPhim(maPhim, new PhimDAO.DanhGiaCallback() {
            @Override
            public void onSuccess(List<DanhGia> danhGiaList) {
                if (!danhGiaList.isEmpty()) {
                    updateRatingOnServer(maPhim, maNguoiDung, rating);
                } else {
                    sendRatingToServer(maPhim, maNguoiDung, rating);
                }
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(ChiTietPhimActivity.this, "Lỗi: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateRatingOnServer(int maPhim, int maNguoiDung, float rating) {
        PhimDAO phimDAO = new PhimDAO();
        phimDAO.updateDanhGiaPhim(maPhim, maNguoiDung, rating, new PhimDAO.UpdateDanhGiaCallback() {
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChiTietPhimActivity.this, "Đã cập nhật đánh giá thành công", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChiTietPhimActivity.this, "Lỗi khi cập nhật đánh giá: " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void sendRatingToServer(int maPhim, int maNguoiDung, float rating) {
        PhimDAO phimDAO = new PhimDAO();
        phimDAO.sendDanhGiaPhim(maPhim, maNguoiDung, rating, new PhimDAO.SendDanhGiaCallback() {
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChiTietPhimActivity.this, "Đã gửi đánh giá thành công", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChiTietPhimActivity.this, "Lỗi khi gửi đánh giá: " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setupBackButton();
    }

    private void setupBackButton() {
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
                Glide.with(ChiTietPhimActivity.this).load(phim.getBanner()).into(posterImageView);
                if (phim.getMoTa() == null || phim.getMoTa().isEmpty()) {
                    tvReadMore.setVisibility(View.GONE);
                    descriptionTextView.setText("Không có mô tả cho phim này.");
                } else {
                    descriptionTextView.setText(phim.getMoTa());
                    tvReadMore.setVisibility(View.VISIBLE);
                }
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
                    ratingTextView.setText("Đánh giá: " + String.format(Locale.getDefault(), "%.1f", averageRating) + "/10");
                    movieRatingBar.setRating((float) averageRating);
                } else {
                    ratingTextView.setText("Chưa có đánh giá");
                }
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(ChiTietPhimActivity.this, "Lỗi: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
