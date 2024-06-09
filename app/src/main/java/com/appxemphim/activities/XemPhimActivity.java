package com.appxemphim.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appxemphim.R;
import com.appxemphim.adapters.TapPhimAdapter;
import com.appxemphim.dao.PhimDAO;
import com.appxemphim.dao.TapPhimDAO;
import com.appxemphim.data.Phim;
import com.appxemphim.data.TapPhim;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;
import java.util.List;

public class XemPhimActivity extends AppCompatActivity {
    private PlayerView playerView;
    private ExoPlayer player;
    private TapPhimDAO tapPhimDAO;
    private TapPhimAdapter tapPhimAdapter;
    private boolean isPlayerInitialized = false;
    private RecyclerView recyclerView;
    private TextView tieude,danhsachphat;
    private int maPhim;
    private  int maTapPhim;
    private String tentap,tenphim;
    StringBuilder title = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_phim);
        playerView = findViewById(R.id.videoView);
        tapPhimDAO = new TapPhimDAO();
        maPhim = getIntent().getIntExtra("MaPhim", -1);
        recyclerView = findViewById(R.id.rvTapPhim);
        tieude = findViewById(R.id.titlePhim);
        danhsachphat = findViewById(R.id.txtDanhSachPhat);

        maTapPhim = -1;
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        tapPhimAdapter = new TapPhimAdapter(getApplicationContext(),new ArrayList<>());
        recyclerView.setAdapter(tapPhimAdapter);
        fetchPhimDetails(maPhim);
    }
    @Override
    protected void onStart() {
        super.onStart();
        back();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void initializePlayer(String videoUrl) {
        if (!isPlayerInitialized) {
            player = new SimpleExoPlayer.Builder(this).build();
            playerView.setPlayer(player);
            MediaItem mediaItem = MediaItem.fromUri(Uri.parse(videoUrl));
            player.setMediaItem(mediaItem);
            player.prepare();
            player.play();
            isPlayerInitialized = true;
        } else {
            // Nếu trình phát đã được khởi tạo, chỉ cần cập nhật video mới
            MediaItem mediaItem = MediaItem.fromUri(Uri.parse(videoUrl));
            player.setMediaItem(mediaItem);
            player.prepare();
            player.play();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
            isPlayerInitialized = false;
        }
    }
    public void back()
    {
        releasePlayer();
        ImageView back = findViewById(R.id.ivBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void loadThongTinTapPhim(int matapphim)
    {
        tapPhimDAO.getTapPhimById(matapphim, new TapPhimDAO.TapPhimCallback() {
            @Override
            public void onSuccess(TapPhim tapPhim) {
                tentap = tapPhim.getTenTap().toString();
                String videoUrls = tapPhim.getLienKetPhim();
                initializePlayer(videoUrls);
                LoadTieuDe();

            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("ProfileFragment", "Failed to fetch data: " + errorMessage);
            }
        });
    }

    private void loaddanhsachtap(int maphim) {

        tapPhimDAO.getTapPhim(maphim, new TapPhimDAO.ListTapPhimCallback() {
            @Override
            public void onSuccess(List<TapPhim> phimList) {

                if (phimList == null || phimList.isEmpty()) {
                    danhsachphat.setText("Chưa có tập phim nào");
                    return;
                }
                if(maTapPhim ==-1)
                {
                    loadThongTinTapPhim(phimList.get(0).getMaTapPhim());
                }

                tapPhimAdapter.updateTapPhimList(phimList);
                tapPhimAdapter.setOnItemClickListener(new TapPhimAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(TapPhim tapPhim) {
                        releasePlayer();
                        loadThongTinTapPhim(tapPhim.getMaTapPhim());

                    }
                });
            }
            @Override
            public void onFailure(String message) {
                Log.e("PhimFragment", "Failed to fetch data: " + message);
            }
        });
    }
    private void fetchPhimDetails(int maPhim) {
        PhimDAO phimDAO = new PhimDAO();
        phimDAO.getPhimById(maPhim, new PhimDAO.PhimByIdCallback() {
            @Override
            public void onSuccess(Phim phim) {
                tenphim = phim.getTieuDe().toString();
                loaddanhsachtap(maPhim);

            }
            @Override
            public void onFailure(String error) {
                Toast.makeText(XemPhimActivity.this, "Không thể lấy thông tin phim", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void LoadTieuDe()
    {
        title.setLength(0);
        title.append(tenphim);
        title.append(" - ");
        title.append(tentap);
        tieude.setText(title.toString());
    }

}