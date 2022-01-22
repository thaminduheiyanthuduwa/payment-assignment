package com.iiit.payment.payment.transation;

import com.iiit.payment.payment.model.PaymentObj;

import java.io.IOException;
import java.util.List;

public interface Payment {

    void save(List<PaymentObj> paymentObj) throws IOException;

    List<PaymentObj> getPayment(String user, String category, String date) throws IOException;

}
