package com.appxemphim.data;

import java.util.Date;

public class BinhLuan {
    private int MaBinhLuan;
    private int MaNguoiDung;
    private int MaPhim;
    private String NoiDung;
    private Date NgayBinhLuan;

    public BinhLuan() {
    }

    public BinhLuan(int maBinhLuan, int maNguoiDung, int maPhim, String noiDung, Date ngayBinhLuan) {
        this.MaBinhLuan = maBinhLuan;
        this.MaNguoiDung = maNguoiDung;
        this.MaPhim = maPhim;
        this.NoiDung = noiDung;
        this.NgayBinhLuan = ngayBinhLuan;
    }

    public BinhLuan(int maPhim, String noiDung) {
    }

    public BinhLuan(int maPhim, int maNguoiDung, String noiDung) {
    }

    public int getMaBinhLuan() {
        return MaBinhLuan;
    }

    public void setMaBinhLuan(int maBinhLuan) {
        this.MaBinhLuan = maBinhLuan;
    }

    public int getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.MaNguoiDung = maNguoiDung;
    }

    public int getMaPhim() {
        return MaPhim;
    }

    public void setMaPhim(int maPhim) {
        this.MaPhim = maPhim;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        this.NoiDung = noiDung;
    }

    public Date getNgayBinhLuan() {
        return NgayBinhLuan;
    }

    public void setNgayBinhLuan(Date ngayBinhLuan) {
        this.NgayBinhLuan = ngayBinhLuan;
    }
}
