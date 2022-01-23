package com.iiit.payment.payment.model;

import java.io.Serializable;

public class ChartObj implements Serializable {

    private String categoryName;
    private Double totalBudget;
    private Double totalExpense;

    public ChartObj(String categoryName, Double totalBudget, Double totalExpense) {
        this.categoryName = categoryName;
        this.totalBudget = totalBudget;
        this.totalExpense = totalExpense;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }
}
