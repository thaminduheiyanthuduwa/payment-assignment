package com.iiit.payment.payment.transaction;

public abstract class Transaction implements Payment {


    protected CreateType createType;

    protected Transaction(CreateType createType){
        this.createType = createType;
    }



}
