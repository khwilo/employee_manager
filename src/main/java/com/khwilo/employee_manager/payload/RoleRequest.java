package com.khwilo.employee_manager.payload;

import javax.validation.constraints.NotBlank;

public class RoleRequest {
    @NotBlank
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
