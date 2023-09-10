package com.example.demo.security.payload;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class AuthRequest {
    @NotNull
    @Length(min = 5, max = 50)
    String email;

    @NotNull
    @Length(min = 5, max = 10)
    private String password;

    public AuthRequest() {
    }

    public AuthRequest(@NotNull @Length(min = 5, max = 50) String email,
            @NotNull @Length(min = 5, max = 10) String password) {
        this.email = email;
        this.password = password;
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

    
}
