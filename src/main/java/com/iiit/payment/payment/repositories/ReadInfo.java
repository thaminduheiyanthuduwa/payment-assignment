package com.iiit.payment.payment.repositories;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.SignUp;

import java.io.IOException;
import java.util.ArrayList;

public interface ReadInfo {

    ArrayList<SignUp> readSignUpDetails() throws IOException;

    ArrayList<Category> readCategories() throws IOException;

}
