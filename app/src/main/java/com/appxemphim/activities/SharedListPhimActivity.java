package com.appxemphim.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.appxemphim.R;
import com.appxemphim.Utils.ItemClickSupport;
import com.appxemphim.adapters.ListPhimAdapter;
import com.appxemphim.data.Phim;

import java.util.ArrayList;

public class SharedListPhimActivity extends AppCompatActivity {

    private TextView titleTextView;
    private RecyclerView recyclerView;
    private ListPhimAdapter phimAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_list_phim);
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
        setContentView(R.layout.activity_shared_list_phim);
        recyclerView = findViewById(R.id.rvListMoives);
        titleTextView = findViewById(R.id.titleLayout);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        phimAdapter = new ListPhimAdapter(getApplicationContext(),new ArrayList<>());
        recyclerView.setAdapter(phimAdapter);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        ArrayList<Phim> data = (ArrayList<Phim>) intent.getSerializableExtra("data");
        titleTextView.setText(title);
        phimAdapter.updatePhimList(data);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
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