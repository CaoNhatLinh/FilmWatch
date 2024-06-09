package com.appxemphim.dao;

import com.appxemphim.Api.ApiClient;
import com.appxemphim.data.Phim_NguoiDung;
import com.appxemphim.data.TapPhim;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Phim_NguoiDungDAO {
    private ApiClient apiClient;

    public Phim_NguoiDungDAO() {
        apiClient = ApiClient.apiClient;
    }

    public void getHistory(int maNguoidung,final Phim_NguoiDungDAO.historyCallback callback) {
        Call<List<Phim_NguoiDung>> call = apiClient.getHistory(maNguoidung);
        call.enqueue(new Callback<List<Phim_NguoiDung>>() {
            @Override
            public void onResponse(Call<List<Phim_NguoiDung>> call, Response<List<Phim_NguoiDung>> response) {
                if (response.isSuccessful()) {
                    List<Phim_NguoiDung> histories = response.body();
                    callback.onSuccess(histories);
                } else {
                    callback.onFailure("Failed to fetch data: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Phim_NguoiDung>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
    public interface historyCallback {
        void onSuccess(List<Phim_NguoiDung> histories);
        void onFailure(String message);
    }
}
