package com.iiit.payment.payment.services;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.transation.Transaction;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface PaymentService {

    ResponseEntity save(String user, PaymentObj paymentObj, String type, String date) throws IOException;

    ResponseEntity edit(String user, PaymentObj paymentObj, String type, Integer id) throws IOException;

    ResponseEntity delete(String user, String type, Integer id) throws IOException;

    ResponseEntity getTotalValues(String user, String type, String date) throws IOException;

    ResponseEntity getPayment(String user, String paymentType, String date, String category, String type) throws IOException;

    ResponseEntity generateCharts(String user, String type) throws  IOException;

    ResponseEntity overallChart(String user, String type) throws  IOException;
}
