package com.example.weighttrackingoml;
// This is used to store login user name and password information for users

public class LoginModel {

    private int id = 1;
    private String username;
    private String password;

    //Constructors
    public LoginModel(String username, String password) {

        id = id++;
        this.username = username;
        this.password = password;
    }

    public LoginModel() {
    }

    // getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //to string method


    @Override
    public String toString() {
        return username;
    }
}
