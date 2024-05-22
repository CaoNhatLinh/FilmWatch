package com.appxemphim.data;

import java.util.Date;

public class Phim
{
    public Phim() {
    }
    private int MaPhim;
    private String TieuDe;
    private String TieuDeQuocTe;
    private String MoTa;
    private int NamPhatHanh;
    private String NgonNgu;
    private String DaoDien;
    private int ThoiLuong;

    public int getMaPhim() {
        return MaPhim;
    }

    public void setMaPhim(int maPhim) {
        MaPhim = maPhim;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getTieuDeQuocTe() {
        return TieuDeQuocTe;
    }

    public void setTieuDeQuocTe(String tieuDeQuocTe) {
        TieuDeQuocTe = tieuDeQuocTe;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getNamPhatHanh() {
        return NamPhatHanh;
    }

    public void setNamPhatHanh(int namPhatHanh) {
        NamPhatHanh = namPhatHanh;
    }

    public String getNgonNgu() {
        return NgonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        NgonNgu = ngonNgu;
    }

    public String getDaoDien() {
        return DaoDien;
    }

    public void setDaoDien(String daoDien) {
        DaoDien = daoDien;
    }

    public int getThoiLuong() {
        return ThoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        ThoiLuong = thoiLuong;
    }

    public String getChatLuong() {
        return ChatLuong;
    }

    public void setChatLuong(String chatLuong) {
        ChatLuong = chatLuong;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public String getAnhBia() {
        return AnhBia;
    }

    public void setAnhBia(String anhBia) {
        AnhBia = anhBia;
    }

    public String getTrailerURL() {
        return TrailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        TrailerURL = trailerURL;
    }

    public Date getNgayThem() {
        return NgayThem;
    }

    public void setNgayThem(Date ngayThem) {
        NgayThem = ngayThem;
    }

    public int getSoTap() {
        return SoTap;
    }

    public void setSoTap(int soTap) {
        SoTap = soTap;
    }

    public int getMaQuocGia() {
        return MaQuocGia;
    }

    public void setMaQuocGia(int maQuocGia) {
        MaQuocGia = maQuocGia;
    }

    public String getDienVien() {
        return DienVien;
    }

    public void setDienVien(String dienVien) {
        DienVien = dienVien;
    }

    private String ChatLuong;
    private String TinhTrang;
    private String AnhBia;
    private String TrailerURL;
    private Date NgayThem;
    private int SoTap;
    private int MaQuocGia;
    private String DienVien;

    public Phim(int maPhim, String tieuDe, String tieuDeQuocTe, String moTa, int namPhatHanh, String ngonNgu, String daoDien, int thoiLuong, String chatLuong, String tinhTrang, String anhBia, String trailerURL, Date ngayThem, int soTap, int maQuocGia, String dienVien) {
        MaPhim = maPhim;
        TieuDe = tieuDe;
        TieuDeQuocTe = tieuDeQuocTe;
        MoTa = moTa;
        NamPhatHanh = namPhatHanh;
        NgonNgu = ngonNgu;
        DaoDien = daoDien;
        ThoiLuong = thoiLuong;
        ChatLuong = chatLuong;
        TinhTrang = tinhTrang;
        AnhBia = anhBia;
        TrailerURL = trailerURL;
        NgayThem = ngayThem;
        SoTap = soTap;
        MaQuocGia = maQuocGia;
        DienVien = dienVien;
    }
}


