package com.iiit.payment.payment.transation;

import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.model.TotalPayments;
import com.iiit.payment.payment.repositories.ReadInfo;
import com.iiit.payment.payment.repositories.SaveInfo;
import com.iiit.payment.payment.repositories.impl.ReadInfoImpl;
import com.iiit.payment.payment.repositories.impl.SaveInfoImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Transaction implements Payment {


    protected CreateType createType;

    protected Transaction(CreateType createType){
        this.createType = createType;
    }



}
