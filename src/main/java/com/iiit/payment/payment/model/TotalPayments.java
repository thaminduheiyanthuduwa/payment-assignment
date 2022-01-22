package com.iiit.payment.payment.model;

import java.io.Serializable;

public class TotalPayments implements Serializable {

    private Double total;
    private Double totalIncome;
    private Double totalExpenses;

    public TotalPayments(Double total, Double totalIncome, Double totalExpenses) {
        this.total = total;
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
}
