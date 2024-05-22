
package com.appxemphim.dao;

import com.appxemphim.Api.ApiClient;
import com.appxemphim.data.Phim;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class PhimDAO {

    private ApiClient apiClient;

    public PhimDAO() {
        apiClient = ApiClient.apiClient;
    }

    public void getPhimList(final PhimCallback callback) {
        Call<List<Phim>> call = apiClient.getlistPhim();
        call.enqueue(new Callback<List<Phim>>() {
            @Override
            public void onResponse(Call<List<Phim>> call, Response<List<Phim>> response) {
                if (response.isSuccessful()) {
                    List<Phim> phimList = response.body();
                    callback.onSuccess(phimList);
                } else {
                    callback.onFailure("Failed to fetch data: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Phim>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public interface PhimCallback {
        void onSuccess(List<Phim> phimList);
        void onFailure(String message);
    }
}


