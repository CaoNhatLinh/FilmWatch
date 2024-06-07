
package com.appxemphim.dao;

import com.appxemphim.Api.ApiClient;
import com.appxemphim.data.Phim;
import com.appxemphim.data.TheLoai;
import com.appxemphim.data.DanhGia;
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
        Call<List<Phim>> call = apiClient.getListPhim();
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

    public void getTop5Phim(final PhimCallback callback) {
        Call<List<Phim>> call = apiClient.getListBanner();
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
    public void getListPhimMoi(final PhimCallback callback) {
        Call<List<Phim>> call = apiClient.getListPhimMoi();
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
    public void getListPhimTrungQuocHot(final PhimCallback callback) {
        Call<List<Phim>> call = apiClient.getListPhimTrungQuocHot();
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
    public void getListPhimAuMyHot(final PhimCallback callback) {
        Call<List<Phim>> call = apiClient.getListPhimAuMyHot();
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


    public void getListPhimDaXem(final PhimCallback callback) {
        Call<List<Phim>> call = apiClient.getListPhimDaXem();
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
    public void getPhimById(int id, final PhimByIdCallback callback) {
        Call<Phim> call = apiClient.getPhimById(id);
        call.enqueue(new Callback<Phim>() {
            @Override
            public void onResponse(Call<Phim> call, Response<Phim> response) {
                if (response.isSuccessful()) {
                    Phim phim = response.body();
                    callback.onSuccess(phim);
                } else {
                    callback.onFailure("Failed to fetch data: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<Phim> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
    public void getDanhGiaPhim(int phimId, final DanhGiaCallback callback) {
        Call<List<DanhGia>> call = apiClient.getDanhGiaPhim(phimId);
        call.enqueue(new Callback<List<DanhGia>>() {
            @Override
            public void onResponse(Call<List<DanhGia>> call, Response<List<DanhGia>> response) {
                if (response.isSuccessful()) {
                    List<DanhGia> danhGiaList = response.body();
                    callback.onSuccess(danhGiaList);
                } else {
                    callback.onFailure("Failed to fetch data: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<DanhGia>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getTheLoaiPhim(int phimId, final TheLoaiCallback callback) {
        Call<List<TheLoai>> call = apiClient.getTheLoaiPhim(phimId);
        call.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                if (response.isSuccessful()) {
                    List<TheLoai> theLoaiList = response.body();
                    callback.onSuccess(theLoaiList);
                } else {
                    callback.onFailure("Failed to fetch data: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public interface PhimCallback {
        void onSuccess(List<Phim> phimList);
        void onFailure(String message);
    }
    public interface PhimByIdCallback {
        void onSuccess(Phim phim);
        void onFailure(String message);
    }
    public interface DanhGiaCallback {
        void onSuccess(List<DanhGia> danhGiaList);
        void onFailure(String message);
    }

    public interface TheLoaiCallback {
        void onSuccess(List<TheLoai> theLoaiList);
        void onFailure(String message);
    }
}


