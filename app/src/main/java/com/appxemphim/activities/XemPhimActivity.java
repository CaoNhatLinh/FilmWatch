package com.appxemphim.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.appxemphim.Api.ApiType;
import com.appxemphim.R;
import com.appxemphim.Utils.ItemClickSupport;
import com.appxemphim.adapters.ListPhimAdapter;
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
    private RecyclerView recyclerView;
    private int maPhim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_phim);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        playerView = findViewById(R.id.videoView);
        tapPhimDAO = new TapPhimDAO();
        maPhim = getIntent().getIntExtra("MaPhim", -1);
        recyclerView = findViewById(R.id.rvTapPhim);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        tapPhimAdapter = new TapPhimAdapter(getApplicationContext(),new ArrayList<>());
        recyclerView.setAdapter(tapPhimAdapter);
        loaddanhsachtap(maPhim);
    }
    @Override
    protected void onStart() {
        super.onStart();
        initializePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ViewGroup.LayoutParams layoutParams = playerView.getLayoutParams();
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            playerView.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

            playerView.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    layoutParams.height = getResources().getDimensionPixelSize(R.dimen.player_height)
            ));
        }

    }
    private void initializePlayer() {
        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);
        String videoUrl = "https://s3.phim1280.tv/20240425/pIrZxiJ1/index.m3u8";
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(videoUrl));
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
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
        }
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
    private void loaddanhsachtap(int maphim) {

        tapPhimDAO.getTapPhim(maphim, new TapPhimDAO.TapPhimCallback() {
            @Override
            public void onSuccess(List<TapPhim> phimList) {
                tapPhimAdapter.updateTapPhimList(phimList);
            }

            @Override
            public void onFailure(String message) {
                Log.e("PhimFragment", "Failed to fetch data: " + message);
            }
        });
    }
}