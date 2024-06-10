
package com.appxemphim.dao;

import com.appxemphim.Api.ApiClient;
import com.appxemphim.data.BinhLuan;
import com.appxemphim.Api.ApiType;
import com.appxemphim.data.Phim;
import com.appxemphim.data.TheLoai;
import com.appxemphim.data.DanhGia;

import java.util.Date;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhimDAO {

    private ApiClient apiClient;

    public PhimDAO() {
        apiClient = ApiClient.apiClient;
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

    public void fetchPhimList(ApiType apiType, final PhimCallback callback) {
        Call<List<Phim>> call = null;

        switch (apiType) {
            case ALL_PHIM:
                call = apiClient.getListPhim();
                break;
            case PHIM_MOI:
                call = apiClient.getListPhimMoi();
                break;
            case TOP_PHIM_TRUNG_QUOC:
                call = apiClient.getListPhimTrungQuocHot();
                break;
            case PHIM_TRUNG_QUOC:
                call = apiClient.getAllListPhimTrungQuoc();
                break;
            case TOP_PHIM_HAN:
                call = apiClient.getListPhimHanHot();
                break;
            case PHIM_HAN:
                call = apiClient.getAllListPhimHan();
                break;
            case TOP_ANIME:
                call = apiClient.getListTopAnime();
                break;
            case ANIME:
                call = apiClient.getAllListAnime();
                break;
            case TOP_LOVE:
                call = apiClient.getListTopLove();
                break;
            case LOVE:
                call = apiClient.getAllListLove();
                break;
        }

        if (call != null) {
            call.enqueue(new Callback<List<Phim>>() {
                @Override
                public void onResponse(Call<List<Phim>> call, Response<List<Phim>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFailure("Failed to fetch data: " + (response.message() != null ? response.message() : "No message"));
                    }
                }

                @Override
                public void onFailure(Call<List<Phim>> call, Throwable t) {
                    callback.onFailure(t.getMessage());
                }
            });
        } else {
            callback.onFailure("Invalid API type");
        }
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
    public void sendDanhGiaPhim(int maPhim, int maNguoiDung, float danhGia, final SendDanhGiaCallback callback) {
        DanhGia danhGiaObj = new DanhGia(maPhim, maNguoiDung, danhGia);
        Call<Void> call = apiClient.sendDanhGiaPhim(maPhim, danhGiaObj);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(); // Gọi callback onSuccess nếu thành công
                } else {
                    callback.onFailure("Server returned: " + response.code()); // Gọi callback onFailure nếu có lỗi
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure(t.getMessage()); // Gọi callback onFailure nếu có lỗi
            }
        });
    }

    public void updateDanhGiaPhim(int maPhim, int maNguoiDung, float danhGiaMoi, final UpdateDanhGiaCallback callback) {
        Call<Void> call = apiClient.updateDanhGiaPhim(maPhim, maNguoiDung, danhGiaMoi); // Gửi yêu cầu cập nhật đánh giá đến máy chủ
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(); // Gọi callback onSuccess nếu thành công
                } else {
                    callback.onFailure("Server returned: " + response.code()); // Gọi callback onFailure nếu có lỗi
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure(t.getMessage()); // Gọi callback onFailure nếu có lỗi
            }
        });
    }
    public void getBinhLuanPhim(int phimId, final BinhLuanCallback callback) {
        Call<List<BinhLuan>> call = apiClient.getBinhLuanPhim(phimId);
        call.enqueue(new Callback<List<BinhLuan>>() {
            @Override
            public void onResponse(Call<List<BinhLuan>> call, Response<List<BinhLuan>> response) {
                if (response.isSuccessful()) {
                    List<BinhLuan> binhLuanList = response.body();
                    callback.onSuccess(binhLuanList);
                } else {
                    callback.onFailure("Failed to fetch data: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<BinhLuan>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
    public void sendBinhLuanPhim(int maPhim, int maNguoiDung, String noiDung, final SendBinhLuanCallback callback) {
        BinhLuan binhLuan = new BinhLuan(maPhim, maNguoiDung, noiDung); // Gán mã phim và mã người dùng vào đối tượng binhLuan
        Call<Void> call = apiClient.sendBinhLuanPhim(maPhim, binhLuan);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess();
                } else {
                    callback.onFailure("Server returned: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }



    public interface BinhLuanCallback {
        void onSuccess(List<BinhLuan> binhLuanList);
        void onFailure(String message);
    }
    public interface SendBinhLuanCallback {
        void onSuccess();
        void onFailure(String error);
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
    public interface SendDanhGiaCallback {
        void onSuccess();
        void onFailure(String error);
    }
    public interface UpdateDanhGiaCallback {
        void onSuccess();
        void onFailure(String error);
    }
}


