package com.appxemphim.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.appxemphim.Api.ApiClient;

import com.appxemphim.R;
import com.appxemphim.adapters.CustomListAdapter;
import com.appxemphim.data.Phim;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> movieTitles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        movieTitles = new ArrayList<>(); // Sử dụng biến movieTitles đã khai báo ở cấp độ lớp
        adapter = new CustomListAdapter(this, R.layout.list_item_layout, movieTitles); // Sử dụng biến adapter đã khai báo ở cấp độ lớp
        listView = findViewById(R.id.listView); // Gán listView vào biến ở cấp độ lớp
        listView.setAdapter(adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = searchEditText.getText().toString();
                if (!title.isEmpty()) {
                    searchMovies(title);
                } else {
                    Toast.makeText(SearchActivity.this, "Please enter a movie title", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void searchMovies(String title) {
        ApiClient.apiClient.searchPhimByTitle(title).enqueue(new Callback<List<Phim>>() {
            @Override
            public void onResponse(Call<List<Phim>> call, Response<List<Phim>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Phim> movies = response.body();
                    movieTitles.clear();
                    for (Phim phim : movies) {
                        movieTitles.add(phim.getTieuDe());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(SearchActivity.this, "No movies found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Phim>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}