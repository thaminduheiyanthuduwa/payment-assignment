package com.iiit.payment.payment.services.impl;

import com.iiit.payment.payment.model.Login;
import com.iiit.payment.payment.model.ResponseObj;
import com.iiit.payment.payment.model.SignUp;
import com.iiit.payment.payment.repositories.ReadInfo;
import com.iiit.payment.payment.repositories.SaveInfo;
import com.iiit.payment.payment.repositories.impl.ReadInfoImpl;
import com.iiit.payment.payment.repositories.impl.SaveInfoImpl;
import com.iiit.payment.payment.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class LoginServiceImpl implements LoginService {

    public ResponseEntity getLoginState(Login login) throws IOException {

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<SignUp> info = readInfo.readSignUpDetails();

        Boolean state;

        state = info.stream().filter(signUp -> signUp.getUserName().equalsIgnoreCase(login.getUserName())
                && signUp.getPassword().equalsIgnoreCase(login.getPassword())).count() > 0;

        ResponseObj responseObj = new ResponseObj();

        if (state) {
            responseObj.setStatus(HttpStatus.OK.value());
            responseObj.setMsg("Success");
            return new ResponseEntity<>(responseObj,HttpStatus.OK);
        }
        else {
            responseObj.setStatus(HttpStatus.BAD_REQUEST.value());
            responseObj.setMsg("No User Found");
            return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity saveUser(SignUp user) throws IOException {

        ArrayList<SignUp> saveObj = new ArrayList<>();

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<SignUp> info = readInfo.readSignUpDetails();
        saveObj.addAll(info);

        Boolean state;

        state = info.stream().filter(signUp -> signUp.getUserName().equalsIgnoreCase(user.getUserName())
                && signUp.getPassword().equalsIgnoreCase(user.getPassword())).count() > 0;

        ResponseObj responseObj = new ResponseObj();

        if (state){
            responseObj.setStatus(HttpStatus.BAD_REQUEST.value());
            responseObj.setMsg("User Already Exists");
            return new ResponseEntity<>(responseObj,HttpStatus.BAD_REQUEST);
        }
        else {
            saveObj.add(user);
            SaveInfo saveInfo = new SaveInfoImpl();
            saveInfo.saveSignUpDetails(saveObj);
            responseObj.setStatus(HttpStatus.OK.value());
            responseObj.setMsg("Successfully Saved The User");
            return new ResponseEntity<>(responseObj,HttpStatus.OK);
        }
    }
}
