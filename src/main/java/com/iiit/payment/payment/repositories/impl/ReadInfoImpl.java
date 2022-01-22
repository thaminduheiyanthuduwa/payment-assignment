package com.iiit.payment.payment.repositories.impl;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.SignUp;
import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.repositories.ReadInfo;
import com.iiit.payment.payment.transation.Budget;

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
    public ArrayList<PaymentObj> readTransaction() throws IOException {

        ArrayList<PaymentObj> transactions = new ArrayList<>();

        try {

            FileInputStream fileInputStream = new FileInputStream("transaction.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            boolean state = false;
            while (state != true) {
                try {

                    PaymentObj transaction = (PaymentObj) objectInputStream.readObject();
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
    public ArrayList<PaymentObj> readBudget() throws IOException {

        ArrayList<PaymentObj> paymentObjs = new ArrayList<>();

        try {

            FileInputStream fileInputStream = new FileInputStream("budget.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            boolean state = false;
            while (state != true) {
                try {

                    PaymentObj paymentObj = (PaymentObj) objectInputStream.readObject();
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
