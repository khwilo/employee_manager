package com.khwilo.employee_manager.payload;

import javax.validation.constraints.NotBlank;

public class RoleRequest {
    @NotBlank
    private String emailAddress;

    @NotBlank
    private String role;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
