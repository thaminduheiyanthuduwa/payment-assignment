package com.iiit.payment.payment.repositories;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.SignUp;
import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.transation.Budget;
import com.iiit.payment.payment.transation.Expense;
import com.iiit.payment.payment.transation.Income;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface SaveInfo {

    void saveSignUpDetails(List<SignUp> signUp) throws IOException;

    void saveCategoryDetails(List<Category> categories) throws IOException;

    void saveIncome(List<Income> transactions) throws IOException;

    void saveExpenses(List<Expense> transactions) throws IOException;

    void saveBudgetDetails(List<PaymentObj> budgets) throws IOException;


}
