package com.appxemphim.Api;
import com.appxemphim.data.BinhLuan;
import com.appxemphim.data.ChangePasswordRequest;
import com.appxemphim.data.LoginRequest;
import com.appxemphim.data.NguoiDung;
import com.appxemphim.data.Phim;
import com.appxemphim.data.TapPhim;
import com.appxemphim.data.TheLoai;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.appxemphim.data.DanhGia;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.POST;

public interface ApiClient {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiClient apiClient = new Retrofit.Builder()
            .baseUrl("https://backend-api-moives-1.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient.class);


    // Phim

    @GET("api/phim")
    Call<List<Phim>> getListPhim();
    @GET("api/phim/banner")
    Call<List<Phim>> getListBanner();
    @GET("api/phim/phimmoi")
    Call<List<Phim>> getListPhimMoi();
    @GET("api/phim/china/top")
    Call<List<Phim>> getListPhimTrungQuocHot();
    @GET("api/phim/china")
    Call<List<Phim>> getAllListPhimTrungQuoc();
    @GET("api/phim/korea/top")
    Call<List<Phim>> getListPhimHanHot();
    @GET("api/phim/korea")
    Call<List<Phim>> getAllListPhimHan();
    @GET("api/phim/banner")
    Call<List<Phim>> getListPhimDaXem();
    @GET("api/phim/anime/top")
    Call<List<Phim>> getListTopAnime();
    @GET("api/phim/anime")
    Call<List<Phim>> getAllListAnime();
    @GET("api/phim/love/top")
    Call<List<Phim>> getListTopLove();
    @GET("api/phim/love")
    Call<List<Phim>> getAllListLove();

    //Tap phim
    @GET("api/tapphim/{id}")
    Call<List<TapPhim>> getListTapPhim(@Path("id") int MaPhim);
    @GET("api/tapphim/xemphim/{matapphim}")
    Call<TapPhim> getTapPhim(@Path("matapphim") int MaTapPhim);
    //Nguoi dung
    @GET("api/nguoidung/{id}")
    Call<NguoiDung> getProfileById(@Path("id") int MaNguoiDung);
    @GET("api/nguoidung/profile/{TenDangNhap}")
    Call<NguoiDung> getProfileByTenDangNhap(@Path("TenDangNhap") String TenDangNhap);
    @PUT("api/nguoidung/{id}")
    Call<NguoiDung> editNguoiDung(@Path("id") int MaNguoiDung, @Body NguoiDung nguoidung);
    //Triệu thêm
    // Đăng nhập
    //Nguoi dung
    @POST("api/nguoidung/login")
    Call<NguoiDung> login(@Body LoginRequest loginRequest);

    @POST("api/nguoidung/register") // Địa chỉ endpoint cho đăng ký
    Call<Void> register(@Body NguoiDung registerRequest);
    @GET("api/phim/{id}")
    Call<Phim> getPhimById(@Path("id") int id);

    // Thay đổi mật khẩu
    @POST("api/nguoidung/change-password")
    Call<Void> changePassword(@Body ChangePasswordRequest changePasswordRequest);

    @POST("api/nguoidung/checkUserExist")
    Call<Void> checkUserExist(@Body String emailOrUsername);


    // Lấy danh sách đánh giá của phim theo ID phim
    @GET("api/phim/{phimId}/danhgia")
    Call<List<DanhGia>> getDanhGiaPhim(@Path("phimId") int phimId);

    // Lấy danh sách thể loại của phim theo ID phim
    @GET("api/phim/{phimId}/theloai")
    Call<List<TheLoai>> getTheLoaiPhim(@Path("phimId") int phimId);
    @POST("api/phim/{phimId}/danhgia")
    Call<Void> sendDanhGiaPhim(@Path("phimId") int phimId, @Body DanhGia danhGia);
    @PUT("api/phim/{phimId}/danhgia/{userId}")
    Call<Void> updateDanhGiaPhim(@Path("phimId") int phimId, @Path("userId") int userId, @Body DanhGia danhGia);
    @GET("api/phim/{phimId}/comments")
    Call<List<BinhLuan>> getBinhLuanPhim(@Path("phimId") int phimId);
    @POST("api/phim/{phimId}/comments")
    Call<Void> sendBinhLuanPhim(@Path("phimId") int phimId, @Body BinhLuan binhLuan);
}

