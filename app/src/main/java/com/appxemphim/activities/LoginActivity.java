package com.appxemphim.activities;
//triệu
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appxemphim.R;
import com.appxemphim.dao.NguoiDungDAO;
import com.appxemphim.data.NguoiDung;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmailOrUsername;
    private EditText editTextPassword;
    private  TextView tvQuenMK;
    private Button buttonLogin;

    //đăng ký
    private TextView tvDK;

    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nguoiDungDAO = new NguoiDungDAO();

        editTextEmailOrUsername = findViewById(R.id.editTextEmailOrUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        tvQuenMK = findViewById(R.id.tvQuenMK);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Ánh xạ TextView tvDK từ layout
        tvDK = findViewById(R.id.tvDK);

        // Thêm sự kiện click cho tvDK
        tvDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển từ LoginActivity sang RegisterActivity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        // Thêm sự kiện click cho tvDK
        tvQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển từ LoginActivity sang RegisterActivity
                Intent intent = new Intent(LoginActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        // thêm sự kiện đăng nhập
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailOrUsername = editTextEmailOrUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (emailOrUsername.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    // Thực hiện đăng nhập
                    performLogin(emailOrUsername, password);
                }
            }
        });
    }

    private void performLogin(String emailOrUsername, String password) {
        nguoiDungDAO.login(emailOrUsername, password, new NguoiDungDAO.LoginCallback() {
            @Override
            public void onSuccess(NguoiDung nguoiDung) {
                // Đăng nhập thành công, xử lý logic tiếp theo ở đây (ví dụ: chuyển sang màn hình chính)
                getProfileNguoiDung(emailOrUsername);

            }

            @Override
            public void onFailure(String message) {
                // Đăng nhập thất bại, thông báo lỗi
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại1: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getProfileNguoiDung(String emailOrUsername)
    {
        nguoiDungDAO.getProfile(emailOrUsername, new NguoiDungDAO.NguoiDungCallback() {
            @Override
            public void onSuccess(NguoiDung nguoiDung) {
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                int userId = nguoiDung.getMaNguoiDung();
                Intent intent = new Intent(LoginActivity.this, TrangChuActivity.class);
                intent.putExtra("userId", userId);
                saveUserIdToSharedPreferences(nguoiDung.getMaNguoiDung());
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String message) {
                System.out.println(message);
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại2: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void saveUserIdToSharedPreferences(int userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
    }
}