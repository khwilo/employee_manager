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

    public static String rolePayload(String adminEmailAddress, String roleName) {
        JsonObject role = new JsonObject();
        role.addProperty("emailAddress", adminEmailAddress);
        role.addProperty("role", roleName);

        return role.toString();
    }

    public static String itemPayload(String adminEmailAddress, String asset, String account, int months) {
        JsonObject item = new JsonObject();
        item.addProperty("adminEmail", adminEmailAddress);
        item.addProperty("asset", asset);
        item.addProperty("account", account);
        item.addProperty("months", months);

        return item.toString();
    }
}
