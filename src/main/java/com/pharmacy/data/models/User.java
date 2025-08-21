package com.pharmacy.data.models;

public class User {
    private String userId;
    private String userName;
    private String password;
    private Role role;



    public User(){}

    public User(String userId, String userName, String hashPassword){
        this.userId = userId;
        this.userName = userName;
        this.password = hashPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
