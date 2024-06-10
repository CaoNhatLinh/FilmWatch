package com.appxemphim.data;
import java.util.Date;

public class DanhGia {
    private int MaDanhGia;

    public DanhGia(int maDanhGia, int maNguoiDung, int maPhim, float danhGia, Date ngayDanhGia) {
        MaDanhGia = maDanhGia;
        MaNguoiDung = maNguoiDung;
        MaPhim = maPhim;
        DanhGia = danhGia;
        NgayDanhGia = ngayDanhGia;
    }


    public DanhGia(int maPhim, int maNguoiDung, float rating) {
        MaPhim=maPhim;
        MaNguoiDung=maNguoiDung;
        DanhGia=rating;
    }
    public DanhGia(int maDanhGia,int maPhim, int maNguoiDung, float rating) {
        MaDanhGia=maDanhGia;
        MaPhim=maPhim;
        MaNguoiDung=maNguoiDung;
        DanhGia=rating;
    }


    public int getMaDanhGia() {
        return MaDanhGia;
    }

    public void setMaDanhGia(int maDanhGia) {
        MaDanhGia = maDanhGia;
    }

    public int getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        MaNguoiDung = maNguoiDung;
    }

    public int getMaPhim() {
        return MaPhim;
    }

    public void setMaPhim(int maPhim) {
        MaPhim = maPhim;
    }

    public float getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(float danhGia) {
        DanhGia = danhGia;
    }

    public Date getNgayDanhGia() {
        return NgayDanhGia;
    }

    public void setNgayDanhGia(Date ngayDanhGia) {
        NgayDanhGia = ngayDanhGia;
    }

    private int MaNguoiDung;
    private int MaPhim;
    private float DanhGia;
    private Date NgayDanhGia;
}
