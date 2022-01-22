package com.iiit.payment.payment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Category implements Serializable {

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("user")
    private String user;

    @JsonCreator
    public Category(String categoryName, String user) {
        this.categoryName = categoryName;
        this.user = user;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
