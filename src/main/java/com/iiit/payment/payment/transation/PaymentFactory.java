package com.iiit.payment.payment.transation;

public class PaymentFactory {

    public Payment getPayment(String type) {

        if(type == null){
            return null;
        }
        if(type.equalsIgnoreCase("transaction")){

            return new Transaction();

        }
        else if (type.equalsIgnoreCase("budget")) {

            return new Budget();

        }
        return null;
    }

}
