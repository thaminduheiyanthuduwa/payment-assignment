package com.iiit.payment.payment.repositories.impl;

import com.iiit.payment.payment.model.SignUp;
import com.iiit.payment.payment.repositories.SaveInfo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveInfoImpl implements SaveInfo {

    public void saveSignUpDetails(SignUp signUp) throws IOException {

        ReadInfoImpl readInfo = new ReadInfoImpl();

        ArrayList<SignUp> signUpInfo = new ArrayList<>();
        signUpInfo.addAll(readInfo.readSignUpDetails());
        signUpInfo.add(signUp);

        if (!signUpInfo.isEmpty()) {
            FileOutputStream fileOutputStream = new FileOutputStream("signup.txt");//save file to write objects
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (SignUp signUpTemp : signUpInfo) {
                objectOutputStream.writeObject(signUpTemp);
            }
            objectOutputStream.close();
            System.out.println("Successfully Saved The Sign Up Info");
        }

    }




}
