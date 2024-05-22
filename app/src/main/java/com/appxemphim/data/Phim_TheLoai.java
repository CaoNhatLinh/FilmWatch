package com.appxemphim.data;

public class Phim_TheLoai {
    private int maPhim;
    private int maTheLoai;

    public Phim_TheLoai(int maPhim, int maTheLoai) {
        this.maPhim = maPhim;
        this.maTheLoai = maTheLoai;
    }

    public Phim_TheLoai() {
    }

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }
}
