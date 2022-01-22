package com.iiit.payment.payment.services;

import com.iiit.payment.payment.model.Login;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface LoginService {

    ResponseEntity getLoginState(Login login) throws IOException;

}
