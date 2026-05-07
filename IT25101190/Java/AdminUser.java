package com.example.medicalstore.admin;

public class AdminUser {

    private String adminId;
    private String name;
    private String email;
    private String password;
    private String level;

    public AdminUser(String adminId, String name, String email, String password, String level) {
        this.adminId  = adminId;
        this.name     = name;
        this.email    = email;
        this.password = password;
        this.level    = level;
    }

    public String getAdminId() {
        return adminId;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getLevel() {
        return level;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public boolean canDeleteUsers() {
        return true;
    }

    public String toFileString() {
        return adminId+","+name+","+email+","+password+","+level;
    }

    public static AdminUser fromFileString(String line) {
        String[] p = line.split(",");
        if (p[4].equals("SUPER"))
            return new SuperAdmin(p[0], p[1], p[2], p[3]);
        return new AdminUser(p[0], p[1], p[2], p[3], "ADMIN");
    }
}
