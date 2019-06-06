package com.khwilo.employee_manager.payload;

import javax.validation.constraints.NotBlank;

public class ItemRequest {
    @NotBlank
    private String adminEmail;

    @NotBlank
    private String asset;

    @NotBlank
    private String account;

    private int months;

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }
}
