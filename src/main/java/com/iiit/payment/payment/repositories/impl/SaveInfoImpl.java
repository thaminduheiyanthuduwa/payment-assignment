package com.iiit.payment.payment.repositories.impl;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.SignUp;
import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.repositories.SaveInfo;
import com.iiit.payment.payment.transation.Expense;
import com.iiit.payment.payment.transation.Income;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class SaveInfoImpl implements SaveInfo {

    public void saveSignUpDetails(List<SignUp> signUp) throws IOException {

        if (!signUp.isEmpty()) {
            FileOutputStream fileOutputStream = new FileOutputStream("signup.txt");//save file to write objects
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (SignUp signUpTemp : signUp) {
                objectOutputStream.writeObject(signUpTemp);
            }
            objectOutputStream.close();
            System.out.println("Successfully Saved The Sign Up Info");
        }

    }

    @Override
    public void saveCategoryDetails(List<Category> categories) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("category.txt");//save file to write objects
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (Category categoryTemp : categories) {
            objectOutputStream.writeObject(categoryTemp);
        }
        objectOutputStream.close();
        System.out.println("Successfully Saved");

    }

    @Override
    public void saveIncome(List<Income> transactions) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("income.txt");//save file to write objects
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (Income transaction : transactions) {
            objectOutputStream.writeObject(transaction);
        }
        objectOutputStream.close();
        System.out.println("Successfully Saved");

    }

    @Override
    public void saveExpenses(List<Expense> transactions) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("expenses.txt");//save file to write objects
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (Expense transaction : transactions) {
            objectOutputStream.writeObject(transaction);
        }
        objectOutputStream.close();
        System.out.println("Successfully Saved");

    }


    @Override
    public void saveBudgetDetails(List<PaymentObj> budgets) throws IOException {


        FileOutputStream fileOutputStream = new FileOutputStream("budget.txt");//save file to write objects
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (PaymentObj budget : budgets) {
            objectOutputStream.writeObject(budget);
        }
        objectOutputStream.close();
        System.out.println("Successfully Saved");

    }


}
