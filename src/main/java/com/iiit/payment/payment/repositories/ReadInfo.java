package com.iiit.payment.payment.repositories;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.SignUp;
import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.transation.Budget;

import java.io.IOException;
import java.util.ArrayList;

public interface ReadInfo {

    ArrayList<SignUp> readSignUpDetails() throws IOException;

    ArrayList<Category> readCategories() throws IOException;

    ArrayList<PaymentObj> readTransaction() throws IOException;

    ArrayList<PaymentObj> readBudget() throws IOException;

}
