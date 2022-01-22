package com.iiit.payment.payment.services.impl;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.Login;
import com.iiit.payment.payment.model.ResponseObj;
import com.iiit.payment.payment.model.SignUp;
import com.iiit.payment.payment.repositories.ReadInfo;
import com.iiit.payment.payment.repositories.SaveInfo;
import com.iiit.payment.payment.repositories.impl.ReadInfoImpl;
import com.iiit.payment.payment.repositories.impl.SaveInfoImpl;
import com.iiit.payment.payment.services.CategoryService;
import com.iiit.payment.payment.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;

public class CategoryServiceImpl implements CategoryService {


    @Override
    public ResponseEntity saveCategory(Category category) throws IOException {

        ArrayList<Category> saveObj = new ArrayList<>();

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Category> info = readInfo.readCategories();
        saveObj.addAll(info);

        Boolean state;

        state = info.stream().filter(obj -> obj.getCategoryName().equalsIgnoreCase(category.getCategoryName())).count() > 0;

        ResponseObj responseObj = new ResponseObj();

        if (state){
            responseObj.setStatus(HttpStatus.BAD_REQUEST.value());
            responseObj.setMsg("Category Already Exists");
            return new ResponseEntity<>(responseObj,HttpStatus.BAD_REQUEST);
        }
        else {
            saveObj.add(category);
            SaveInfo saveInfo = new SaveInfoImpl();
            saveInfo.saveCategoryDetails(saveObj);
            responseObj.setStatus(HttpStatus.OK.value());
            responseObj.setMsg("Successfully Saved The Category");
            return new ResponseEntity<>(responseObj,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity getAllCategories() throws IOException {

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Category> info = readInfo.readCategories();

        ResponseObj responseObj = new ResponseObj();
        responseObj.setStatus(HttpStatus.OK.value());
        responseObj.setMsg("Success");
        responseObj.setObject(info);
        return new ResponseEntity<>(responseObj,HttpStatus.OK);
    }

}
