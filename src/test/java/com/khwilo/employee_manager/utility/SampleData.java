package com.khwilo.employee_manager.utility;

import com.google.gson.JsonObject;

public class SampleData {

    public static String createEmployee(String firstName, String lastName, String emailAddress, String password) {
        JsonObject employee = new JsonObject();
        employee.addProperty("firstName", firstName);
        employee.addProperty("lastName", lastName);
        employee.addProperty("emailAddress", emailAddress);
        employee.addProperty("password", password);

        return employee.toString();
    }

    public static String loginEmployee(String emailAddress, String password) {
        JsonObject employee = new JsonObject();
        employee.addProperty("emailAddress", emailAddress);
        employee.addProperty("password", password);

        return employee.toString();
    }

    public static String rolePayload(String roleName) {
        JsonObject role = new JsonObject();
        role.addProperty("role", roleName);

        return role.toString();
    }
}
