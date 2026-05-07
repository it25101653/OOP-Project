package com.example.medicalstore.admin;

public class SuperAdmin extends AdminUser {

    public SuperAdmin(String adminId, String name, String email, String password) {
        super(adminId, name, email, password, "SUPER");
    }

    @Override
    public boolean canDeleteUsers() {
        return true;
    }

    public boolean canManageAdmins() {
        return true;
    }
}