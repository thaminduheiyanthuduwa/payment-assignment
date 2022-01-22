package com.iiit.payment.payment.transation;

import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.model.TotalPayments;

import java.io.IOException;
import java.util.List;

public interface Payment {

    void save(String user, PaymentObj paymentObj, String type, String date) throws IOException;

    Boolean edit(String user, PaymentObj paymentObj, String type, Integer id) throws IOException;

    Boolean delete(String user, String type, Integer id) throws IOException;

    TotalPayments getTotalValues(String user, String date) throws IOException;

    List<PaymentObj> getPayment(String user, String category, String date, String type) throws IOException;

}
