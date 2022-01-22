package com.iiit.payment.payment.services;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.Login;
import com.iiit.payment.payment.model.SignUp;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface CategoryService {

    ResponseEntity saveCategory(String user, Category category) throws IOException;

    ResponseEntity getAllCategories(String category) throws IOException;

    ResponseEntity deleteCategory(String category) throws IOException;

}
