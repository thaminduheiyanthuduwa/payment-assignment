package com.iiit.payment.payment.repositories;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.SignUp;

import java.io.IOException;
import java.util.List;

public interface SaveInfo {

    void saveSignUpDetails(List<SignUp> signUp) throws IOException;

    void saveCategoryDetails(List<Category> categories) throws IOException;


}
