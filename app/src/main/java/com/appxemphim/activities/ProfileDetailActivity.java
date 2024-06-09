package com.appxemphim.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.appxemphim.R;
import com.appxemphim.dao.NguoiDungDAO;
import com.appxemphim.data.NguoiDung;

public class ProfileDetailActivity extends AppCompatActivity {
    private NguoiDungDAO nguoiDungDAO;
    private EditText name,tendangnhap,email;
    int MaNguoiDung;
    private Button btnGTChangePass,btnEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        name = findViewById(R.id.editTextFullName);
        tendangnhap = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmail);
        Intent intent = getIntent();
        MaNguoiDung = intent.getIntExtra("MaNguoiDung",-1);
        nguoiDungDAO = new NguoiDungDAO();
        btnEdit = findViewById(R.id.buttonUpdate);
        btnGTChangePass = findViewById(R.id.btnGTChangePassword);
        btnGTChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileDetailActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile(MaNguoiDung);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        getProfileById(MaNguoiDung);
        back();
    }
    public void back()
    {
        ImageView back = findViewById(R.id.ivBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Lấy dữ liệu chỉnh sửa từ các EditText
        String hoten = name.getText().toString();
        Intent resultIntent = new Intent();
        String hoTen1 =  name.getText().toString();
        resultIntent.putExtra("hoten",hoTen1);
        setResult(RESULT_OK, resultIntent);
        super.onBackPressed();
    }
    public void getProfileById(int id)
    {
        nguoiDungDAO.getProfileById(id, new NguoiDungDAO.NguoiDungCallback() {
            @Override
            public void onSuccess(NguoiDung nguoiDung) {

                name.setText(nguoiDung.getHoTen());
                tendangnhap.setText(nguoiDung.getTenDangNhap());
                email.setText(nguoiDung.getEmail());
            }
            @Override
            public void onFailure(String errorMessage) {
                Log.e("ProfileFragment", "Failed to fetch data: " + errorMessage);
            }
        });
    }
    public void editProfile(int id)
    {
        String hoTen1 =  name.getText().toString();
        String tenDangNhap1 = tendangnhap.getText().toString();
        String email1 = email.getText().toString();
        nguoiDungDAO.editNguoiDung(id, new NguoiDung(id, hoTen1, tenDangNhap1, email1), new NguoiDungDAO.NguoiDungCallback() {
            @Override
            public void onSuccess(NguoiDung nguoiDung) {
                Toast.makeText(ProfileDetailActivity.this, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                name.setText(nguoiDung.getHoTen());
                tendangnhap.setText(nguoiDung.getTenDangNhap());
                email.setText(nguoiDung.getEmail());


            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(ProfileDetailActivity.this, "Chỉnh sửa thất bại", Toast.LENGTH_SHORT).show();
            }
        });

    }
}