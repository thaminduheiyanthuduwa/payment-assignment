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

    @RequestMapping(value = "/{user_name}" ,method = RequestMethod.GET ,headers="Accept=application/json")
    public ResponseEntity getPayment(@PathVariable(value = "user_name") String user,
                                       @RequestParam(value = "payment_type") String paymentType,
                                       @RequestParam(value = "type") String type,
                                       @RequestParam(value = "category") String category,
                                       @RequestParam(value = "date") String date) throws IOException {


        PaymentService paymentService = new PaymentServiceImpl();

        return paymentService.getPayment(user, paymentType, date, category, type);
    }

    @RequestMapping(value = "/edit_payment/{user_name}/{type}/{id}" ,method = RequestMethod.POST ,headers="Accept=application/json")
    public ResponseEntity editPayment(@PathVariable(value = "user_name") String user,
                                      @PathVariable(value = "type") String type,
                                      @PathVariable(value = "id") Integer id,
                                      @RequestBody PaymentObj paymentObj) throws IOException {


        PaymentService paymentService = new PaymentServiceImpl();

        return paymentService.edit(user, paymentObj, type, id);

    }

}
