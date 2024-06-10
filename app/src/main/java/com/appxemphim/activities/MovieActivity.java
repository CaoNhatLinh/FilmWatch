package com.appxemphim.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appxemphim.Api.ApiClient;
import com.appxemphim.R;
import com.appxemphim.adapters.CountryAdapter;
import com.appxemphim.adapters.MovieAdapter;
import com.appxemphim.data.Phim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    private ListView countryListView;
    private ListView movieListView;
    private CountryAdapter countryAdapter;
    private MovieAdapter movieAdapter;

    private List<String> countries = Arrays.asList("China", "Korea", "Anime", "Love");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        countryListView = findViewById(R.id.country_list);
        movieListView = findViewById(R.id.movie_list);

        countryAdapter = new CountryAdapter(this, countries);
        movieAdapter = new MovieAdapter(this, new ArrayList<>());

        countryListView.setAdapter(countryAdapter);
        movieListView.setAdapter(movieAdapter);

        // Xử lý sự kiện click trên danh sách phim
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Phim selectedPhim = movieAdapter.getItem(position);
                if (selectedPhim != null) {


                        Intent intent = new Intent(MovieActivity.this, ChiTietPhimActivity.class);
                        intent.putExtra("MaPhim", selectedPhim.getMaPhim());
                        startActivity(intent);

                }
            }
        });

        // Xử lý sự kiện click trên danh sách quốc gia
        countryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = countries.get(position);
                fetchMoviesByCountry(selectedCountry);
            }
        });
    }

    private void fetchMoviesByCountry(String country) {
        Call<List<Phim>> call;
        switch (country) {
            case "China":
                call = ApiClient.apiClient.getAllListPhimTrungQuoc();
                break;
            case "Korea":
                call = ApiClient.apiClient.getAllListPhimHan();
                break;
            case "Anime":
                call = ApiClient.apiClient.getAllListAnime();
                break;
            case "Love":
                call = ApiClient.apiClient.getAllListLove();
                break;
            default:
                call = ApiClient.apiClient.getListPhim();
                break;
        }

        call.enqueue(new Callback<List<Phim>>() {
            @Override
            public void onResponse(Call<List<Phim>> call, Response<List<Phim>> response) {
                if (response.isSuccessful()) {
                    List<Phim> movies = response.body();
                    movieAdapter.clear();
                    if (movies != null) {
                        movieAdapter.addAll(movies);
                    }
                } else {
                    Toast.makeText(MovieActivity.this, "Failed to fetch movies", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Phim>> call, Throwable t) {
                Toast.makeText(MovieActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
