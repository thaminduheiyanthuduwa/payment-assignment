package com.iiit.payment.payment.services.impl;

import com.iiit.payment.payment.model.ChartObj;
import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.model.ResponseObj;
import com.iiit.payment.payment.model.TotalPayments;
import com.iiit.payment.payment.services.PaymentService;
import com.iiit.payment.payment.transation.Payment;
import com.iiit.payment.payment.transation.PaymentFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
            responseObj.setMsg("No payment found for id "+id);
            return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity delete(String user, String type, Integer id) throws IOException {

        PaymentFactory paymentFactory = new PaymentFactory();

        Payment paymentType = paymentFactory.getPayment(type);

        Boolean state = paymentType.delete(user, type, id);

        ResponseObj responseObj = new ResponseObj();

        if (state) {
            responseObj.setStatus(HttpStatus.OK.value());
            responseObj.setMsg("Success");
            return new ResponseEntity<>(responseObj, HttpStatus.OK);
        }
        else {
            responseObj.setStatus(HttpStatus.BAD_REQUEST.value());
            responseObj.setMsg("No payment found for id "+id);
            return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getTotalValues(String user, String type, String date) throws IOException {

        PaymentFactory paymentFactory = new PaymentFactory();

        Payment paymentType = paymentFactory.getPayment(type);

        TotalPayments state = paymentType.getTotalValues(user, date);

        ResponseObj responseObj = new ResponseObj();

        responseObj.setStatus(HttpStatus.OK.value());
        responseObj.setMsg("Success");
        responseObj.setObject(state);
        return new ResponseEntity<>(responseObj, HttpStatus.OK);

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

    @Override
    public ResponseEntity generateCharts(String user, String date) throws IOException {
        PaymentFactory paymentFactory = new PaymentFactory();
        LocalDateTime futureDate = LocalDateTime.now().plusMonths(1);

        Payment paymentTypeTransactions = paymentFactory.getPayment("transaction");

        List<PaymentObj> paymentExpense  = paymentTypeTransactions.getPayment(user, "all",date , "all");

        Payment paymentTypeBudget = paymentFactory.getPayment("budget");

        List<PaymentObj> paymentBudget  = paymentTypeBudget.getPayment(user, "all",date , "all");
        List<ChartObj> chartObj =  new ArrayList<>();
        if(paymentBudget.size()!=0){
            for(PaymentObj budget : paymentBudget) {
                double sum = paymentExpense.stream().filter(o -> o.getCategory().equalsIgnoreCase(budget.getCategory()) ).mapToDouble(o -> o.getAmount()).sum();
                chartObj.add(new ChartObj(budget.getCategory(),budget.getAmount(),sum));
            }
        }else{
            for(PaymentObj expense : paymentExpense) {
                double sum = paymentExpense.stream().filter(o -> o.getCategory().equalsIgnoreCase(expense.getCategory()) ).mapToDouble(o -> o.getAmount()).sum();

                chartObj.add(new ChartObj(expense.getCategory(),0.0,sum));
            }
        }


        ResponseObj responseObj = new ResponseObj();

        responseObj.setStatus(HttpStatus.OK.value());
        responseObj.setMsg("Success");
        responseObj.setObject(chartObj);
        return new ResponseEntity<>(responseObj, HttpStatus.OK);

    }

    @Override
    public ResponseEntity overallChart(String user, String date) throws IOException {
        PaymentFactory paymentFactory = new PaymentFactory();

        Payment paymentTypeTransaction = paymentFactory.getPayment("transaction");


        TotalPayments state = paymentTypeTransaction.getTotalValues(user, date);

        Payment paymentTypeBudget = paymentFactory.getPayment("budget");


        TotalPayments stateBudget = paymentTypeBudget.getTotalValues(user, date);

        ChartObj chartObj = new ChartObj(null,stateBudget.getTotal(), state.getTotalExpenses());

        ResponseObj responseObj = new ResponseObj();

        responseObj.setStatus(HttpStatus.OK.value());
        responseObj.setMsg("Success");
        responseObj.setObject(chartObj);
        return new ResponseEntity<>(responseObj, HttpStatus.OK);
    }

}
