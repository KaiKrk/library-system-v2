package com.OC.librarybatch.Entity;

public class AuthDetails {

    public String email;

    public String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthDetails{" +
                "email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
