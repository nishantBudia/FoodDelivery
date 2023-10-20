package com.nishant.FoodDelivery.controller;


import com.nishant.FoodDelivery.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("guest")
public class GuestController {
    @Autowired
    GuestService service;

    /*
    POST
     */

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
