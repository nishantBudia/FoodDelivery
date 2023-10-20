package com.nishant.FoodDelivery.controller;


import com.nishant.FoodDelivery.model.Customer;
import com.nishant.FoodDelivery.model.dto.CustomerSignUpDTO;
import com.nishant.FoodDelivery.service.GuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("guest")
public class GuestController {
    @Autowired
    GuestService service;

    /*
    POST
     */

    @PostMapping("customer")
    public String registerCustomer(@RequestBody @Valid CustomerSignUpDTO customerSignUpDTO){
        return service.registerCustomer(customerSignUpDTO);
    }

    /*
    GET
     */

    @GetMapping("filter")
    public String verifyGuestFilter(){
        return "Guest access level";
    }

    /*
    PUT
     */

    /*
    DELETE
     */
}
