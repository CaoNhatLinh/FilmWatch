package com.appxemphim.dao;

import android.util.Log;

import com.appxemphim.Api.ApiClient;
import com.appxemphim.data.NguoiDung;
import com.appxemphim.data.Phim;
import com.appxemphim.data.TapPhim;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TapPhimDAO {
    private ApiClient apiClient;

    public TapPhimDAO() {
        apiClient = ApiClient.apiClient;
    }
    public void getTapPhim(int maphim,final TapPhimDAO.ListTapPhimCallback callback) {
        Call<List<TapPhim>> call = apiClient.getListTapPhim(maphim);
        call.enqueue(new Callback<List<TapPhim>>() {
            @Override
            public void onResponse(Call<List<TapPhim>> call, Response<List<TapPhim>> response) {
                if (response.isSuccessful()) {
                    List<TapPhim> phimList = response.body();
                    callback.onSuccess(phimList);
                } else {
                    callback.onFailure("Failed to fetch data: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<TapPhim>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
    public void getTapPhimById(int mataphim, final TapPhimDAO.TapPhimCallback callback) {
        Call<TapPhim> call = apiClient.getTapPhim(mataphim);


        call.enqueue(new Callback<TapPhim>() {
            @Override
            public void onResponse(Call<TapPhim> call, Response<TapPhim> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TapPhim tapPhim = response.body();
                    callback.onSuccess(tapPhim);
                } else {
                    String errorMessage = "Failed to fetch data: " + response.message();

                    // Log error response
                    Log.e("API Response", errorMessage);

                    callback.onFailure(errorMessage);
                }
            }
            @Override
            public void onFailure(Call<TapPhim> call, Throwable t) {
                String errorMessage = "API call failed: " + t.getMessage();
                callback.onFailure(errorMessage);
            }
        });
    }
    public interface ListTapPhimCallback {
        void onSuccess(List<TapPhim> phimList);
        void onFailure(String message);
    }
    public interface TapPhimCallback {
        void onSuccess(TapPhim tapPhim);
        void onFailure(String message);
    }
}