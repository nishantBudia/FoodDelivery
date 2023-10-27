package com.nishant.FoodDelivery.main.controller;


import com.nishant.FoodDelivery.main.model.Address;
import com.nishant.FoodDelivery.main.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;



    /*
    POST
     */

    @PostMapping("sign-out")
    public String logoutCustomer(@RequestHeader(name = "Authorization") String token){
        return customerService.logoutCustomer(token);
    }

    @PostMapping("address")
    public String addAddress(
            @RequestBody @Valid Address address,
            @RequestHeader(name = "Authorization") String token){
        return customerService.addAddress(token, address);
    }

    /*
    GET
     */

    @GetMapping("filter")
    public String verifyCustomerFilter(){
        return "Customer access level";
    }

    @GetMapping("addresses")
    public List<Address> getAddressesForCustomer(
            @RequestHeader(name = "Authorization") String token){
        return customerService.getAllUserAddresses(token);
    }

    /*
    PUT
     */

    /*
    DELETE
     */

    @DeleteMapping("addresses")
    public String deleteUserAddresses(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody Set<Long> addresses){
        return customerService.deleteUserAddresses(token,addresses);
    }
}
