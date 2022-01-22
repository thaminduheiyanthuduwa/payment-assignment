package com.iiit.payment.payment.repositories.impl;

import com.iiit.payment.payment.model.Login;
import com.iiit.payment.payment.model.SignUp;
import com.iiit.payment.payment.repositories.ReadInfo;
import org.apache.juli.logging.Log;

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

}
