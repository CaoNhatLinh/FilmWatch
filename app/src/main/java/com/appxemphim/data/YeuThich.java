package com.appxemphim.data;


public class YeuThich {
    private int MaNguoiDung;
    private int MaPhim;

    public YeuThich(int maNguoiDung, int maPhim) {
        MaNguoiDung = maNguoiDung;
        MaPhim = maPhim;
    }
    public YeuThich(){

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


}
