package com.nishant.FoodDelivery.main.controller;

import com.nishant.FoodDelivery.main.model.food.FoodCategory;
import com.nishant.FoodDelivery.main.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;



    /*
    POST
     */

    @PostMapping("sign-out")
    public String logoutAdmin(@RequestHeader(name = "Authorization") String token){
        return adminService.logoutAdmin(token);
    }

    @PostMapping("food-categories")
    public List<FoodCategory> addFoodCategories(@RequestBody List<FoodCategory> foodCategories){
        return adminService.addFoodCategories(foodCategories);
    }

    /*
    GET
     */

    @GetMapping("filter")
    public String verifyAdminFilter(){
        return "Admin access level";
    }

    /*
    PUT
     */

    /*
    DELETE
     */
}
