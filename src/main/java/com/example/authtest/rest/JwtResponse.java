package com.example.authtest.rest;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;

    private String login;

    private List<String> userTypes;

    public JwtResponse(String token, String login, List<String> userTypes) {
        this.token = token;
        this.login = login;
        this.userTypes = userTypes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
