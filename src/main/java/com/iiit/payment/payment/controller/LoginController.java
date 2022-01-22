package com.iiit.payment.payment.controller;

import com.iiit.payment.payment.model.Login;
import com.iiit.payment.payment.model.SignUp;
import com.iiit.payment.payment.services.LoginService;
import com.iiit.payment.payment.services.impl.LoginServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @RequestMapping(value = "/{username}/{password}" ,method = RequestMethod.GET ,headers="Accept=application/json")
    public ResponseEntity getLoginState(@PathVariable("username") String user
            , @PathVariable("password") String password) throws IOException {


        Login login = new Login(user, password);

        LoginService loginService = new LoginServiceImpl();
        return loginService.getLoginState(login);

    }

    @RequestMapping(value = "/" ,method = RequestMethod.POST ,headers="Accept=application/json")
    public ResponseEntity saveUserInfo(@RequestBody SignUp user) throws IOException {

        LoginService loginService = new LoginServiceImpl();
        return loginService.saveUser(user);

    }


}
