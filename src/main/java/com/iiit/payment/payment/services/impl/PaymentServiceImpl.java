package com.iiit.payment.payment.services.impl;

import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.model.ResponseObj;
import com.iiit.payment.payment.repositories.ReadInfo;
import com.iiit.payment.payment.repositories.impl.ReadInfoImpl;
import com.iiit.payment.payment.services.PaymentService;
import com.iiit.payment.payment.transation.Payment;
import com.iiit.payment.payment.transation.PaymentFactory;
import com.iiit.payment.payment.transation.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Override
    public ResponseEntity save(String user, PaymentObj paymentObj, String type, String date) throws IOException {

        PaymentFactory paymentFactory = new PaymentFactory();

        Payment paymentType = paymentFactory.getPayment(type);

        paymentType.save(user, paymentObj, type, date);

        ResponseObj responseObj = new ResponseObj();

        responseObj.setStatus(HttpStatus.OK.value());
        responseObj.setMsg("Success");
        return new ResponseEntity<>(responseObj, HttpStatus.OK);

    }

    @Override
    public ResponseEntity edit(String user, PaymentObj paymentObj, String type, Integer id) throws IOException {

        PaymentFactory paymentFactory = new PaymentFactory();

        Payment paymentType = paymentFactory.getPayment(type);

        Boolean state = paymentType.edit(user, paymentObj, type, id);

        ResponseObj responseObj = new ResponseObj();

        if (state) {
            responseObj.setStatus(HttpStatus.OK.value());
            responseObj.setMsg("Success");
            return new ResponseEntity<>(responseObj, HttpStatus.OK);
        }
        else {
            responseObj.setStatus(HttpStatus.BAD_REQUEST.value());
            responseObj.setMsg("Fail");
            return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity getPayment(String user, String paymentState, String date, String category, String type) throws IOException {

        PaymentFactory paymentFactory = new PaymentFactory();

        Payment paymentType = paymentFactory.getPayment(paymentState);

        List<PaymentObj> payment  = paymentType.getPayment(user, category, date, type);

        ResponseObj responseObj = new ResponseObj();

        responseObj.setStatus(HttpStatus.OK.value());
        responseObj.setMsg("Success");
        responseObj.setObject(payment);
        return new ResponseEntity<>(responseObj, HttpStatus.OK);

    }
}
