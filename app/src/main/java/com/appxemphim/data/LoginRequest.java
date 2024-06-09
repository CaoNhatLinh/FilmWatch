package com.appxemphim.data;
//triệu thêm
public class LoginRequest {
    public Integer getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(Integer maNguoiDung) {
        MaNguoiDung = maNguoiDung;
    }

    private Integer MaNguoiDung;
    private String emailOrUsername;
    private String password;

    public LoginRequest(String emailOrUsername, String password) {
        this.emailOrUsername = emailOrUsername;
        this.password = password;
    }

    public String getEmailOrUsername() {
        return emailOrUsername;
    }

    public void setEmailOrUsername(String emailOrUsername) {
        this.emailOrUsername = emailOrUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
