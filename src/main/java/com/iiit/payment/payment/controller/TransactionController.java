package com.iiit.payment.payment.controller;

import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.services.PaymentService;
import com.iiit.payment.payment.services.impl.PaymentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class TransactionController {

    @RequestMapping(value = "/{user_name}/{type}/{date}" ,method = RequestMethod.POST ,headers="Accept=application/json")
    public ResponseEntity savePayment(@PathVariable(value = "user_name") String user,
                                       @PathVariable(value = "type") String type,
                                       @PathVariable(value = "date") String date,
                                       @RequestBody PaymentObj paymentObj) throws IOException {


        PaymentService paymentService = new PaymentServiceImpl();

        return paymentService.save(user, paymentObj, type, date);

    }


    @RequestMapping(value = "/{user_name}/{type}/{category}/{date}" ,method = RequestMethod.GET ,headers="Accept=application/json")
    public ResponseEntity getPayment(@PathVariable(value = "user_name") String user,
                                       @PathVariable(value = "type") String type,
                                       @PathVariable(value = "category") String category,
                                       @PathVariable(value = "date") String date) throws IOException {


        PaymentService paymentService = new PaymentServiceImpl();

        return paymentService.getPayment(user, type, date, category);

    }

}
