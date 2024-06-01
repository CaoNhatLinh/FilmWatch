package com.appxemphim.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.appxemphim.R;
import com.appxemphim.dao.NguoiDungDAO;
import com.appxemphim.data.NguoiDung;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonRegister;

    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nguoiDungDAO = new NguoiDungDAO();

        editTextEmail = findViewById(R.id.etEmail);
        editTextUsername = findViewById(R.id.etTenDN);
        editTextPassword = findViewById(R.id.etMK);
        editTextConfirmPassword = findViewById(R.id.etXNMK);
        buttonRegister = findViewById(R.id.btnDangKi);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();

                if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    // Thực hiện đăng ký
                    performRegistration(email, username, password);
                }
            }
        });
    }

    private void performRegistration(String email, String username, String password) {
        NguoiDung nguoiDung = new NguoiDung(username, password, email);
        nguoiDungDAO.register(nguoiDung, new NguoiDungDAO.RegisterCallback() {
            @Override
            public void onSuccess() {
                // Đăng ký thành công
                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish(); // Đóng activity đăng ký và quay lại màn hình đăng nhập
            }

            @Override
            public void onFailure(String message) {
                // Đăng ký thất bại, hiển thị thông báo lỗi
                Toast.makeText(RegisterActivity.this, "Đăng ký thất bại: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
