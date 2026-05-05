package com.example.medicalstore.user;

public abstract class User {

    private String userId;
    private String name;
    private String email;
    private String password;

    public User(String userId, String name, String email, String password) {
        this.userId  = userId;
        this.name  = name;
        this.email = email;
        this.password = password;
    }

    public String getUserId(){
        return userId;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public abstract String getRole();

    public String toFileString() {
        return userId + "," + name + "," + email + "," + password + "," + getRole();
    }

    public static User fromFileString(String line) {
        String[] p = line.split(",");
        return new Customer(p[0], p[1], p[2], p[3]);
    }
}
