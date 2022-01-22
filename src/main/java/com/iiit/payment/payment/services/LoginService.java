package com.iiit.payment.payment.services;

import com.iiit.payment.payment.model.Login;
import com.iiit.payment.payment.model.SignUp;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface LoginService {

    ResponseEntity getLoginState(Login login) throws IOException;

    ResponseEntity saveUser(SignUp signUp) throws IOException;

}
