package com.iiit.payment.payment.repositories.impl;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.SignUp;
import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.repositories.ReadInfo;
import com.iiit.payment.payment.transation.Budget;
import com.iiit.payment.payment.transation.BudgetEntity;
import com.iiit.payment.payment.transation.Expense;
import com.iiit.payment.payment.transation.Income;

import java.io.*;
import java.util.ArrayList;

public class ReadInfoImpl implements ReadInfo {

    public ArrayList<SignUp> readSignUpDetails() throws IOException {

        ArrayList<SignUp> signUpList = new ArrayList<>();

        try {

            FileInputStream fileInputStream = new FileInputStream("signup.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            boolean state = false;
            while (state != true) {
                try {

                    SignUp race = (SignUp) objectInputStream.readObject();
                    signUpList.add(race);

                } catch (Exception e) {
                    state = true;
                }
            }
            objectInputStream.close();
        } catch (Exception e) {

        }

        return signUpList;

    }

    @Override
    public ArrayList<Category> readCategories() throws IOException {

        ArrayList<Category> categoryArrayList = new ArrayList<>();

        try {

            FileInputStream fileInputStream = new FileInputStream("category.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            boolean state = false;
            while (state != true) {
                try {

                    Category category = (Category) objectInputStream.readObject();
                    categoryArrayList.add(category);

                } catch (Exception e) {
                    state = true;
                }
            }
            objectInputStream.close();
        } catch (Exception e) {

        }

        return categoryArrayList;
    }

    @Override
    public ArrayList<Income> readIncome() throws IOException {

        ArrayList<Income> transactions = new ArrayList<>();

        try {

            FileInputStream fileInputStream = new FileInputStream("income.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            boolean state = false;
            while (state != true) {
                try {

                    Income transaction = (Income) objectInputStream.readObject();
                    transactions.add(transaction);

                } catch (Exception e) {
                    state = true;
                }
            }
            objectInputStream.close();
        } catch (Exception e) {

        }

        return transactions;

    }

    @Override
    public ArrayList<Expense> readExpenses() throws IOException {

        ArrayList<Expense> transactions = new ArrayList<>();

        try {

            FileInputStream fileInputStream = new FileInputStream("expenses.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            boolean state = false;
            while (state != true) {
                try {

                    Expense transaction = (Expense) objectInputStream.readObject();
                    transactions.add(transaction);

                } catch (Exception e) {
                    state = true;
                }
            }
            objectInputStream.close();
        } catch (Exception e) {

        }

        return transactions;

    }

    @Override
    public ArrayList<BudgetEntity> readBudget() throws IOException {

        ArrayList<BudgetEntity> paymentObjs = new ArrayList<>();

        try {

            FileInputStream fileInputStream = new FileInputStream("budget.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            boolean state = false;
            while (state != true) {
                try {

                    BudgetEntity paymentObj = (BudgetEntity) objectInputStream.readObject();
                    paymentObjs.add(paymentObj);

                } catch (Exception e) {
                    state = true;
                }
            }
            objectInputStream.close();
        } catch (Exception e) {

        }

        return paymentObjs;
    }

}
