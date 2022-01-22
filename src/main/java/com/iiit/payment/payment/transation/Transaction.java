package com.iiit.payment.payment.transation;

import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.repositories.ReadInfo;
import com.iiit.payment.payment.repositories.SaveInfo;
import com.iiit.payment.payment.repositories.impl.ReadInfoImpl;
import com.iiit.payment.payment.repositories.impl.SaveInfoImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Transaction implements Payment {

    @Override
    public void save(List<PaymentObj> paymentObj) throws IOException {

        SaveInfo saveInfo = new SaveInfoImpl();
        saveInfo.saveTransactionDetails(paymentObj);

    }

    @Override
    public List<PaymentObj> getPayment(String user, String category, String date, String type) throws IOException {

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<PaymentObj> allTransaction = readInfo.readTransaction();

        ArrayList<PaymentObj> returnObj = new ArrayList<>();

        if (category.equalsIgnoreCase("all") && type.equalsIgnoreCase("all")){
            returnObj.addAll(allTransaction.stream().filter(paymentObj -> paymentObj.getDate().equalsIgnoreCase(date)
                    && paymentObj.getUser().equalsIgnoreCase(user)).collect(Collectors.toList()));
        }
        else if (category.equalsIgnoreCase("all") && !type.equalsIgnoreCase("all")){
            returnObj.addAll(allTransaction.stream().filter(paymentObj -> paymentObj.getDate().equalsIgnoreCase(date)
                    && paymentObj.getUser().equalsIgnoreCase(user)
                    && paymentObj.getType().equalsIgnoreCase(type)).collect(Collectors.toList()));
        }
        else if (!category.equalsIgnoreCase("all") && type.equalsIgnoreCase("all")){
            returnObj.addAll(allTransaction.stream().filter(paymentObj -> paymentObj.getDate().equalsIgnoreCase(date)
                    && paymentObj.getUser().equalsIgnoreCase(user)
                    && paymentObj.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList()));
        }

        return returnObj;
    }

}
