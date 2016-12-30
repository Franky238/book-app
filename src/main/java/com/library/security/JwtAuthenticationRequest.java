package com.library.security;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable {

    private String username;
    private String password;
    private Boolean rememberMe;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }
}
