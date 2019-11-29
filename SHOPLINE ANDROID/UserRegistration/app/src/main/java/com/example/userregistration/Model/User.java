package com.example.userregistration.Model;

public class User {
    String userId;
    String name;
    String email;
    String password;
    String address;
    String mobileNo;
    public User(){

    }

    public User(String userId,String name, String email, String password, String address, String mobileNo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.mobileNo = mobileNo;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}
