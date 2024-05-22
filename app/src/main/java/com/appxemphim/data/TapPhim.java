package com.appxemphim.data;

public class TapPhim {
    private int maTapPhim;
    private String tenTap;
    private String moTa;
    private int maPhim;
    private String lienKetPhim;

    public TapPhim() {
    }

    public TapPhim(int maTapPhim, String tenTap, String moTa, int maPhim, String lienKetPhim) {
        this.maTapPhim = maTapPhim;
        this.tenTap = tenTap;
        this.moTa = moTa;
        this.maPhim = maPhim;
        this.lienKetPhim = lienKetPhim;
    }

    public int getMaTapPhim() {
        return maTapPhim;
    }

    public void setMaTapPhim(int maTapPhim) {
        this.maTapPhim = maTapPhim;
    }

    public String getTenTap() {
        return tenTap;
    }

    public void setTenTap(String tenTap) {
        this.tenTap = tenTap;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public String getLienKetPhim() {
        return lienKetPhim;
    }

    public void setLienKetPhim(String lienKetPhim) {
        this.lienKetPhim = lienKetPhim;
    }
}
