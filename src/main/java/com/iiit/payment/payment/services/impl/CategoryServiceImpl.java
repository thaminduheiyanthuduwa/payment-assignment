package com.iiit.payment.payment.services.impl;

import com.iiit.payment.payment.model.*;
import com.iiit.payment.payment.repositories.ReadInfo;
import com.iiit.payment.payment.repositories.SaveInfo;
import com.iiit.payment.payment.repositories.impl.ReadInfoImpl;
import com.iiit.payment.payment.repositories.impl.SaveInfoImpl;
import com.iiit.payment.payment.services.CategoryService;
import com.iiit.payment.payment.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Override
    public ResponseEntity saveCategory(String user, Category category) throws IOException {

        ArrayList<Category> saveObj = new ArrayList<>();

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Category> info = readInfo.readCategories();
        saveObj.addAll(info);

        Boolean state;

        state = info.stream().filter(obj -> obj.getCategoryName().equalsIgnoreCase(category.getCategoryName())
                && obj.getUser().equalsIgnoreCase(category.getUser())).count() > 0;

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
    public ResponseEntity getAllCategories(String cat) throws IOException {

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Category> info = readInfo.readCategories();

        List<Category> filteredList = info.stream().filter(category -> category.getUser()
                .equalsIgnoreCase(cat)).collect(Collectors.toList());

        ResponseObj responseObj = new ResponseObj();
        responseObj.setStatus(HttpStatus.OK.value());
        responseObj.setMsg("Success");
        responseObj.setObject(filteredList);
        return new ResponseEntity<>(responseObj,HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteCategory(String category) throws IOException {

        ReadInfo readInfo = new ReadInfoImpl();

        ArrayList<PaymentObj> paymentObjs = new ArrayList<>();
        ArrayList<Category> categoryArrayList = new ArrayList<>();

        paymentObjs.addAll(readInfo.readBudget());
        paymentObjs.addAll(readInfo.readTransaction());
        categoryArrayList.addAll(readInfo.readCategories());

        ResponseObj responseObj = new ResponseObj();

        if (categoryArrayList.stream().filter(category1 -> category1
                .getCategoryName().equalsIgnoreCase(category)).collect(Collectors.toList()).isEmpty()){
            responseObj.setStatus(HttpStatus.BAD_REQUEST.value());
            responseObj.setMsg("Cannot delete the category. Category does not exists");
            return new ResponseEntity<>(responseObj,HttpStatus.BAD_REQUEST);
        }
        else if (!paymentObjs.stream().filter(paymentObj -> paymentObj.getCategory()
                .equalsIgnoreCase(category)).collect(Collectors.toList()).isEmpty()){

            responseObj.setStatus(HttpStatus.BAD_REQUEST.value());
            responseObj.setMsg("Cannot delete the category. Already used in payment");
            return new ResponseEntity<>(responseObj,HttpStatus.BAD_REQUEST);
        }
        else {
            categoryArrayList.removeAll(categoryArrayList.stream().filter(category1 -> category1
                    .getCategoryName().equalsIgnoreCase(category)).collect(Collectors.toList()));
            SaveInfo saveInfo = new SaveInfoImpl();
            saveInfo.saveCategoryDetails(categoryArrayList);
            responseObj.setStatus(HttpStatus.OK.value());
            responseObj.setMsg("Successfully deleted the category");
            return new ResponseEntity<>(responseObj,HttpStatus.BAD_REQUEST);
        }


    }

}
