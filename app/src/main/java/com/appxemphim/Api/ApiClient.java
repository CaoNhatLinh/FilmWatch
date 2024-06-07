package com.appxemphim.Api;
import com.appxemphim.data.LoginRequest;
import com.appxemphim.data.NguoiDung;
import com.appxemphim.data.Phim;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
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
    @GET("api/phim/banner")
    Call<List<Phim>> getListPhimMoi();
    @GET("api/phim/banner")
    Call<List<Phim>> getListPhimTrungQuocHot();
    @GET("api/phim/banner")
    Call<List<Phim>> getListPhimAuMyHot();
    @GET("api/phim/banner")
    Call<List<Phim>> getListPhimDaXem();

    //Nguoi dung
    @GET("api/nguoidung/{id}")
    Call<NguoiDung> getProfileById(@Path("id") int MaNguoiDung);

    
    //Triệu thêm
    // Đăng nhập
    //Nguoi dung
    @POST("api/nguoidung/login")
    Call<NguoiDung> login(@Body LoginRequest loginRequest);

    @POST("api/nguoidung/register") // Địa chỉ endpoint cho đăng ký
    Call<Void> register(@Body NguoiDung registerRequest);
    }

