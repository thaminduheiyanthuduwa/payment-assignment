package com.iiit.payment.payment.repositories;

import com.iiit.payment.payment.model.SignUp;

import java.io.IOException;

public interface SaveInfo {

    void saveSignUpDetails(SignUp signUp) throws IOException;


}
