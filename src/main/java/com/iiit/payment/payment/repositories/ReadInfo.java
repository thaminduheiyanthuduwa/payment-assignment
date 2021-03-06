package com.iiit.payment.payment.repositories;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.SignUp;
import com.iiit.payment.payment.model.Budget;
import com.iiit.payment.payment.model.Expense;
import com.iiit.payment.payment.model.Income;

import java.io.IOException;
import java.util.ArrayList;

public interface ReadInfo {

    ArrayList<SignUp> readSignUpDetails() throws IOException;

    ArrayList<Category> readCategories() throws IOException;

    ArrayList<Income> readIncome() throws IOException;

    ArrayList<Expense> readExpenses() throws IOException;

    ArrayList<Budget> readBudget() throws IOException;

}
