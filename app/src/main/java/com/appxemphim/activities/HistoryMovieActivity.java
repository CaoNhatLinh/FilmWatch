package com.appxemphim.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.appxemphim.R;
import com.appxemphim.Utils.ItemClickSupport;
import com.appxemphim.adapters.ListPhimAdapter;
import com.appxemphim.dao.PhimDAO;
import com.appxemphim.data.Phim;
import java.util.List;
import java.util.ArrayList;
public class HistoryMovieActivity extends AppCompatActivity {
    private RecyclerView rvHistoryMovies;
    private PhimDAO phimDAO;
    private ListPhimAdapter phimAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onResume() {
        super.onResume();
        loadPhiDaXem();
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
    private void loadPhiDaXem() {
        setContentView(R.layout.activity_history_movie);
        rvHistoryMovies = findViewById(R.id.rvHistoryMoives);
        rvHistoryMovies.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        phimAdapter = new ListPhimAdapter(getApplicationContext(),new ArrayList<>());
        rvHistoryMovies.setAdapter(phimAdapter);
        phimDAO = new PhimDAO();
        phimDAO.getListPhimDaXem(new PhimDAO.PhimCallback() {
            @Override
            public void onSuccess(List<Phim> phimList) {
                phimAdapter.updatePhimList(phimList);
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
                Phim phim = phimAdapter.getPhimAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), ChiTietPhimActivity.class);
                intent.putExtra("MaPhim", phim.getMaPhim());
                startActivity(intent);
            }
        });
    }
}