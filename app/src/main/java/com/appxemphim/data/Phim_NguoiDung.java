package com.appxemphim.data;

public class Phim_NguoiDung {
    public Phim_NguoiDung(int maPhim, int maNguoiDung, int maTapPhim) {
        MaPhim = maPhim;
        MaNguoiDung = maNguoiDung;
        MaTapPhim = maTapPhim;
    }

    public Phim_NguoiDung(int maPhim_NguoiDung, int maPhim, int maNguoiDung, int maTapPhim) {
        this.maPhim_NguoiDung = maPhim_NguoiDung;
        MaPhim = maPhim;
        MaNguoiDung = maNguoiDung;
        MaTapPhim = maTapPhim;
    }

    public int getMaPhim_NguoiDung() {
        return maPhim_NguoiDung;
    }

    public void setMaPhim_NguoiDung(int maPhim_NguoiDung) {
        this.maPhim_NguoiDung = maPhim_NguoiDung;
    }

    public int getMaPhim() {
        return MaPhim;
    }

    public void setMaPhim(int maPhim) {
        MaPhim = maPhim;
    }

    public int getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        MaNguoiDung = maNguoiDung;
    }

    public int getMaTapPhim() {
        return MaTapPhim;
    }

    public void setMaTapPhim(int maTapPhim) {
        MaTapPhim = maTapPhim;
    }

    private int maPhim_NguoiDung;
    private int MaPhim;
    private int MaNguoiDung;
    private int MaTapPhim;
}
