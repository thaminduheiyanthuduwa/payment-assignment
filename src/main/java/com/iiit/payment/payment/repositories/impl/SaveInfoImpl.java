package com.iiit.payment.payment.repositories.impl;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.SignUp;
import com.iiit.payment.payment.repositories.SaveInfo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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

        if (!categories.isEmpty()) {
            FileOutputStream fileOutputStream = new FileOutputStream("category.txt");//save file to write objects
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Category categoryTemp : categories) {
                objectOutputStream.writeObject(categoryTemp);
            }
            objectOutputStream.close();
            System.out.println("Successfully Saved");
        }

    }


}
