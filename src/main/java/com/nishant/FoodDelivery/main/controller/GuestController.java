package com.nishant.FoodDelivery.main.controller;


import com.nishant.FoodDelivery.main.model.dto.AdminSigninDTO;
import com.nishant.FoodDelivery.main.model.dto.CustomerSignUpDTO;
import com.nishant.FoodDelivery.main.model.dto.CustomerSigninDTO;
import com.nishant.FoodDelivery.main.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("guest")
public class GuestController {
    @Autowired
    AuthService authService;

    /*
    POST
     */

    @PostMapping()
    public String registerCustomer(@RequestBody @Valid CustomerSignUpDTO customerSignUpDTO){
        return authService.registerCustomer(customerSignUpDTO.getEmail(),customerSignUpDTO.getUsername(),customerSignUpDTO.getPassword());
    }

    @PostMapping("customer")
    public String loginCustomer(@RequestBody @Valid CustomerSigninDTO customerSigninDTO){
        return authService.loginCustomer(customerSigninDTO.getEmail(),customerSigninDTO.getPassword());
    }

    @PostMapping("admin")
    public String loginAdmin(@RequestBody @Valid AdminSigninDTO adminSigninDTO){
        return authService.loginAdmin(adminSigninDTO.getUsername(),adminSigninDTO.getPassword());
    }

    /*
    GET
     */

    @GetMapping("filter")
    public String verifyGuestFilter(){
        return "Guest access level";
    }

    @GetMapping("verification")
    public String verifyEmail(@RequestParam String token, @RequestParam String email){
        return authService.verifyEmail(token,email);
    }

    /*
    PUT
     */

    /*
    DELETE
     */
}
