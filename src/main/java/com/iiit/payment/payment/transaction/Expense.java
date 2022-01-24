package com.iiit.payment.payment.transaction;

import com.iiit.payment.payment.model.Category;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Expense implements CreateType, Serializable {

    private Integer id;
    private String name;
    private Category category;
    private String type;
    private Double amount;
    private String notes;
    private String recurring;
    private String user;
    private String date;
    private LocalDateTime dateTime;

    public Expense(Integer id, String name, String category, String type, Double amount,
                   String notes, String recurring, String user, String date, LocalDateTime dateTime) {
        this.id = id;
        this.name = name;
        this.category = new Category(category,user);
        this.type = type;
        this.amount = amount;
        this.notes = notes;
        this.recurring = recurring;
        this.user = user;
        this.date = date;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRecurring() {
        return recurring;
    }

    public void setRecurring(String recurring) {
        this.recurring = recurring;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
