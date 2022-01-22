package com.iiit.payment.payment.controller;

import com.iiit.payment.payment.model.Category;
import com.iiit.payment.payment.model.Login;
import com.iiit.payment.payment.model.SignUp;
import com.iiit.payment.payment.services.CategoryService;
import com.iiit.payment.payment.services.LoginService;
import com.iiit.payment.payment.services.impl.CategoryServiceImpl;
import com.iiit.payment.payment.services.impl.LoginServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {

    @RequestMapping(value = "/{user_name}" ,method = RequestMethod.POST ,headers="Accept=application/json")
    public ResponseEntity saveCategory(@PathVariable(value = "user_name") String user,
                                       @RequestBody Category category) throws IOException {

        CategoryService categoryService = new CategoryServiceImpl();
        category.setUser(user);
        return categoryService.saveCategory(user, category);
    }

    @RequestMapping(value = "/{user_name}" ,method = RequestMethod.GET ,headers="Accept=application/json")
    public ResponseEntity getAllCategories(@PathVariable(value = "user_name") String user) throws IOException {

        CategoryService categoryService = new CategoryServiceImpl();
        return categoryService.getAllCategories(user);

    }


}
