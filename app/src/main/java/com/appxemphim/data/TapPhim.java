package com.appxemphim.data;

public class TapPhim {
    private int MaTapPhim;
    private String TenTap;
    private String MoTa;
    private int MaPhim;

    public TapPhim(int maTapPhim, String tenTap, String moTa, int maPhim, String lienKetPhim) {
        MaTapPhim = maTapPhim;
        TenTap = tenTap;
        MoTa = moTa;
        MaPhim = maPhim;
        LienKetPhim = lienKetPhim;
    }

    public int getMaTapPhim() {
        return MaTapPhim;
    }

    public void setMaTapPhim(int maTapPhim) {
        MaTapPhim = maTapPhim;
    }

    public String getTenTap() {
        return TenTap;
    }

    public void setTenTap(String tenTap) {
        TenTap = tenTap;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getMaPhim() {
        return MaPhim;
    }

    public void setMaPhim(int maPhim) {
        MaPhim = maPhim;
    }

    public String getLienKetPhim() {
        return LienKetPhim;
    }

    public void setLienKetPhim(String lienKetPhim) {
        LienKetPhim = lienKetPhim;
    }

    private String LienKetPhim;

    public TapPhim() {
    }


}
