package com.nishant.FoodDelivery.controller;

import com.nishant.FoodDelivery.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminRepo adminRepo;



    /*
    POST
     */

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
