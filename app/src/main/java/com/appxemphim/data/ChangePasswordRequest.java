package com.appxemphim.data;

public class ChangePasswordRequest {
    private String emailOrUsername;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordRequest(String emailOrUsername, String oldPassword, String newPassword) {
        this.emailOrUsername = emailOrUsername;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getEmailOrUsername() {
        return emailOrUsername;
    }

    public void setEmailOrUsername(String emailOrUsername) {
        this.emailOrUsername = emailOrUsername;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
