package com.iiit.payment.payment.transaction;

public class PaymentFactory {

    public Payment getPayment(String type) {

        if(type == null){
            return null;
        }
        if(type.equalsIgnoreCase("transaction")){

            return new TransactionImpl(null);

        }
        else if (type.equalsIgnoreCase("budget")) {

            return new BudgetImpl();

        }
        return null;
    }

}
