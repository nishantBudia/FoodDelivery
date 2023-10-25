package com.nishant.FoodDelivery.controller;

import com.nishant.FoodDelivery.repo.AdminRepo;
import com.nishant.FoodDelivery.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;



    /*
    POST
     */

    @PostMapping("sign-out")
    public String signoutAdmin(@RequestHeader(name = "Authorization") String token){
        return adminService.signoutAdmin(token);
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
