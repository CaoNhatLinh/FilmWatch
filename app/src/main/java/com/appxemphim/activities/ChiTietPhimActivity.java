package com.appxemphim.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.appxemphim.R;
import com.appxemphim.dao.PhimDAO;
import com.appxemphim.data.Phim;

public class ChiTietPhimActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phim);
        int maPhim = getIntent().getIntExtra("MaPhim", -1);
        PhimDAO phimDAO = new PhimDAO();
//        Phim phim = phimDAO.getPhimById(maPhim);
//        if (phim != null) {
//            // Display movie details
//        }
    }
}