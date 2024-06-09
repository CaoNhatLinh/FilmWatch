package com.appxemphim.dao;

import com.appxemphim.Api.ApiClient;
import com.appxemphim.data.YeuThich;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YeuThichDao {

    // Thêm phim yêu thích mới cho người dùng
    public void addYeuThich(int maNguoiDung, int maPhim, Callback<Void> callback) {
        // Tạo đối tượng YeuThich với mã người dùng và mã phim được cung cấp
        YeuThich yeuThich = new YeuThich();
        yeuThich.setMaNguoiDung(maNguoiDung);
        yeuThich.setMaPhim(maPhim);

        // Gọi API để thêm phim yêu thích mới
        Call<Void> call = ApiClient.apiClient.addYeuThich(yeuThich);
        // Thực hiện request bất đồng bộ và xử lý kết quả thông qua callback
        call.enqueue(callback);
    }

    // Xóa phim yêu thích của người dùng
    public void deleteYeuThich(int maNguoiDung, int maPhim, Callback<Void> callback) {
        // Gọi API để xóa phim yêu thích
        Call<Void> call = ApiClient.apiClient.deleteYeuThich(maNguoiDung, maPhim);
        // Thực hiện request bất đồng bộ và xử lý kết quả thông qua callback
        call.enqueue(callback);
    }

    // Lấy danh sách phim yêu thích của người dùng theo ID người dùng
    public void getListYeuThichByUserId(int maNguoiDung, Callback<List<YeuThich>> callback) {
        // Gọi API để lấy danh sách phim yêu thích
        Call<List<YeuThich>> call = ApiClient.apiClient.getListYeuThichByUserId(maNguoiDung);
        // Thực hiện request bất đồng bộ và xử lý kết quả thông qua callback
        call.enqueue(callback);
    }


}
