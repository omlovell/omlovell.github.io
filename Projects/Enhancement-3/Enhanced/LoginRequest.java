package com.example.weighttrackingoml;

public class LoginRequest {

    // login information userEmail and password
    private String email;
    private String password;

    public LoginRequest(String email, String password) {

        if (email != null && password != null) {
            this.email = email;
            this.password = password;
        }

    }
}
