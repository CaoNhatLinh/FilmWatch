package com.appxemphim.data;


import java.util.Date;

public class NguoiDung {
    private int MaNguoiDung;
    private String TenDangNhap;
    private String MatKhau;

        public int getMaNguoiDung() {
            return MaNguoiDung;
        }

        public void setMaNguoiDung(int maNguoiDung) {
            MaNguoiDung = maNguoiDung;
        }

        public String getTenDangNhap() {
            return TenDangNhap;
        }

        public void setTenDangNhap(String tenDangNhap) {
            TenDangNhap = tenDangNhap;
        }

        public String getMatKhau() {
            return MatKhau;
        }

        public void setMatKhau(String matKhau) {
            MatKhau = matKhau;
        }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getAnhDaiDien() {
        return AnhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        AnhDaiDien = anhDaiDien;
    }

    public Date getNgayDangKy() {
        return NgayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        NgayDangKy = ngayDangKy;
    }

    private String Email;
    private String HoTen;

    public NguoiDung(int maNguoiDung, String tenDangNhap, String matKhau, String email, String hoTen, String gioiTinh, Date ngaySinh, String anhDaiDien, Date ngayDangKy) {
        MaNguoiDung = maNguoiDung;
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
        Email = email;
        HoTen = hoTen;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
        AnhDaiDien = anhDaiDien;
        NgayDangKy = ngayDangKy;
    }

    private String GioiTinh;
    private Date NgaySinh;
    private String AnhDaiDien;
    private Date NgayDangKy;

    public NguoiDung() {
    }


}
