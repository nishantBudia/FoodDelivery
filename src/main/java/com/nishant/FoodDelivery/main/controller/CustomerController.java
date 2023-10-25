package com.nishant.FoodDelivery.main.controller;


import com.nishant.FoodDelivery.main.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;



    /*
    POST
     */

    @PostMapping("sign-out")
    public String signoutCustomer(@RequestHeader(name = "Authorization") String token){
        return customerService.signoutCustomer(token);
    }

    /*
    GET
     */

    @GetMapping("filter")
    public String verifyCustomerFilter(){
        return "Customer access level";
    }

    /*
    PUT
     */

    /*
    DELETE
     */
}
