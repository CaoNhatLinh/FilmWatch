package com.appxemphim.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.appxemphim.R;
import com.appxemphim.dao.NguoiDungDAO;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText etEmailOrUsername, etMatKhauCu, etMatKhauMoi;
    private Button btnDoiMatKhau;
    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etEmailOrUsername = findViewById(R.id.etEmailOrUsername);
        etMatKhauCu = findViewById(R.id.etMatKhauCu);
        etMatKhauMoi = findViewById(R.id.etMatKhauMoi);
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau);
        nguoiDungDAO = new NguoiDungDAO();

        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doiMatKhau();
                Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        back();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void back() {
        ImageView back = findViewById(R.id.ivBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void doiMatKhau() {
        String emailOrUsername = etEmailOrUsername.getText().toString().trim();
        String matKhauCu = etMatKhauCu.getText().toString().trim();
        String matKhauMoi = etMatKhauMoi.getText().toString().trim();

        if (emailOrUsername.isEmpty() || matKhauCu.isEmpty() || matKhauMoi.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        nguoiDungDAO.changePassword(emailOrUsername, matKhauCu, matKhauMoi, new NguoiDungDAO.ChangePasswordCallback() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(ChangePasswordActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(ChangePasswordActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}