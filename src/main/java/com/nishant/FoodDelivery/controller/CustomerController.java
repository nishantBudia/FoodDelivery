package com.nishant.FoodDelivery.controller;


import com.nishant.FoodDelivery.model.dto.CustomerSignUpDTO;
import com.nishant.FoodDelivery.model.dto.CustomerSigninDTO;
import com.nishant.FoodDelivery.service.CustomerService;
import jakarta.validation.Valid;
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
