package com.iiit.payment.payment.services;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.transation.Transaction;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface PaymentService {

    ResponseEntity save(String user, PaymentObj paymentObj, String type, String date) throws IOException;

    ResponseEntity getPayment(String user, String paymentType, String date, String category, String type) throws IOException;


}
