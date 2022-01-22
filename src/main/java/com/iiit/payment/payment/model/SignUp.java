package com.iiit.payment.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

public class SignUp implements Serializable {

    @JsonProperty("name")
    private String name;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("password")
    private String password;

    public SignUp(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
