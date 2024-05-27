package com.appxemphim.dao;

import android.util.Log;

import com.appxemphim.Api.ApiClient;
import com.appxemphim.data.NguoiDung;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NguoiDungDAO {
    private ApiClient apiClient;

    public NguoiDungDAO() {
        apiClient = ApiClient.apiClient;
    }
    public void getProfileById(int MaNguoiDung, final NguoiDungCallback callback) {
        Call<NguoiDung> call = apiClient.getProfileById(MaNguoiDung);

        // Log URL request
        Log.d("API Request", call.request().url().toString());

        call.enqueue(new Callback<NguoiDung>() {
            @Override
            public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NguoiDung nguoiDung = response.body();

                    Log.d("API Response", "Success: " + nguoiDung.getMaNguoiDung());

                    callback.onSuccess(nguoiDung);
                } else {
                    String errorMessage = "Failed to fetch data: " + response.message();

                    // Log error response
                    Log.e("API Response", errorMessage);

                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<NguoiDung> call, Throwable t) {
                String errorMessage = "API call failed: " + t.getMessage();

                // Log failure response
                Log.e("API Failure", errorMessage);

                callback.onFailure(errorMessage);
            }
        });
    }

    public interface NguoiDungCallback {
        void onSuccess(NguoiDung nguoiDung);
        void onFailure(String message);
    }

}
