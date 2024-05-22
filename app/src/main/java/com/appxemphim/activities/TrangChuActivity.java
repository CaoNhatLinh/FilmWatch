package com.appxemphim.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appxemphim.Api.ApiClient;
import com.appxemphim.R;
import com.appxemphim.data.Phim;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TrangChuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, new ListPhimFragment())
                    .commit();
        }
    }

}