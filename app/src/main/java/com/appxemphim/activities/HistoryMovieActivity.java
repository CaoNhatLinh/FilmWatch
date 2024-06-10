package com.appxemphim.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.appxemphim.R;
import com.appxemphim.Utils.ItemClickSupport;
import com.appxemphim.adapters.ListPhimAdapter;
import com.appxemphim.adapters.historyAdapter;
import com.appxemphim.dao.PhimDAO;
import com.appxemphim.dao.Phim_NguoiDungDAO;
import com.appxemphim.dao.TapPhimDAO;
import com.appxemphim.data.Phim;
import com.appxemphim.data.Phim_NguoiDung;

import java.util.List;
import java.util.ArrayList;
public class HistoryMovieActivity extends AppCompatActivity {
    private RecyclerView rvHistoryMovies;
    private PhimDAO phimDAO;
    private historyAdapter historyAdapter;
    private Phim_NguoiDungDAO phimNguoiDungDAO;
    private int maNguoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maNguoiDung = getCurrentUserID();
        if (maNguoiDung == -1) {
            Toast.makeText(this, "Không thể lấy mã người dùng. Vui lòng đăng nhập lại.", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    private int getCurrentUserID() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getInt("userId", -1);
    }
    @Override
    public void onResume() {
        super.onResume();
        loadPhiDaXem(maNguoiDung);
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
    private void loadPhiDaXem(int MaNguoiDung) {
        setContentView(R.layout.activity_history_movie);
        rvHistoryMovies = findViewById(R.id.rvHistoryMoives);
        rvHistoryMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        historyAdapter = new historyAdapter(getApplicationContext(),new ArrayList<>());
        rvHistoryMovies.setAdapter(historyAdapter);
        phimNguoiDungDAO = new Phim_NguoiDungDAO();
        phimNguoiDungDAO.getHistory(MaNguoiDung,new Phim_NguoiDungDAO.historiesCallback() {
            @Override
            public void onSuccess(List<Phim_NguoiDung> histories) {
                historyAdapter.updatePhimList(histories);
            }
            @Override
            public void onFailure(String message) {
                Log.e("PhimFragment", "Failed to fetch data: " + message);
            }
        });
        ItemClickSupport.addTo(rvHistoryMovies).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // Handle item click here
                Phim_NguoiDung phim_nguoiDung = historyAdapter.getPhimAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), XemPhimActivity.class);
                intent.putExtra("MaTapPhim", phim_nguoiDung.getMaTapPhim());
                intent.putExtra("MaPhim", phim_nguoiDung.getMaPhim());
                startActivity(intent);
            }
        });
    }
}