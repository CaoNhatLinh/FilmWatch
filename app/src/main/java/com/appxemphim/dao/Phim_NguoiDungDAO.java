package com.appxemphim.dao;

import com.appxemphim.Api.ApiClient;
import com.appxemphim.data.Phim_NguoiDung;
import com.appxemphim.data.TapPhim;
import com.appxemphim.data.YeuThich;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Phim_NguoiDungDAO {
    private ApiClient apiClient;

    public Phim_NguoiDungDAO() {
        apiClient = ApiClient.apiClient;
    }

    public void getHistory(int maNguoidung,final Phim_NguoiDungDAO.historiesCallback callback) {
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
    public void getHistoryByMaPhim(int maNguoidung,int maPhim,final Phim_NguoiDungDAO.historyCallback callback) {
        Call<Phim_NguoiDung> call = apiClient.getHistoryByMaPhim(maNguoidung,maPhim);
        call.enqueue(new Callback<Phim_NguoiDung>() {
            @Override
            public void onResponse(Call<Phim_NguoiDung> call, Response<Phim_NguoiDung> response) {
                if (response.isSuccessful()) {
                    Phim_NguoiDung history = response.body();
                    callback.onSuccess(history);
                } else {
                    callback.onFailure("Failed to fetch data: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<Phim_NguoiDung> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void addHistory(int maNguoiDung, int maPhim,int maTapPhim, Callback<Void> callback) {
        Phim_NguoiDung history = new Phim_NguoiDung(maPhim,maNguoiDung,maTapPhim);
        Call<Void> call = ApiClient.apiClient.addHistory(history);
        call.enqueue(callback);
    }
    public void editHistory(int maPhim_NguoiDung,int maNguoiDung, int maPhim,int maTapPhim, Callback<Void> callback) {
        Phim_NguoiDung history = new Phim_NguoiDung(maPhim,maNguoiDung,maTapPhim);
        Call<Void> call = ApiClient.apiClient.editHistory(maPhim_NguoiDung,history);
        call.enqueue(callback);
    }

    public interface historiesCallback {
        void onSuccess(List<Phim_NguoiDung> histories);
        void onFailure(String message);
    }
    public interface historyCallback {
        void onSuccess(Phim_NguoiDung history);
        void onFailure(String message);
    }
}
