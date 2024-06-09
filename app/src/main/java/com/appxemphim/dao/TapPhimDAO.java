package com.appxemphim.dao;

import com.appxemphim.Api.ApiClient;
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
    public void getTapPhim(int maphim,final TapPhimDAO.TapPhimCallback callback) {
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

    public void getTapPhimById(int maTapPhim, TapPhimCallback profileFragment) {
    }

    public interface TapPhimCallback {
        void onSuccess(List<TapPhim> phimList);
        void onFailure(String message);
    }
}
