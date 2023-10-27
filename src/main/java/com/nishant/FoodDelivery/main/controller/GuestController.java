package com.nishant.FoodDelivery.main.controller;


import com.nishant.FoodDelivery.main.model.dto.*;
import com.nishant.FoodDelivery.main.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GuestController {
    @Autowired
    AuthService authService;

    @RestController
    @RequestMapping("guest/registration")
    public class RegistrationController{
        @PostMapping("customer")
        public String registerCustomer(@RequestBody @Valid CustomerRegistrationDTO customerRegistrationDTO){
            return authService.registerCustomer(customerRegistrationDTO.getEmail(), customerRegistrationDTO.getPassword());
        }

        @PostMapping("restaurant-owner")
        public String registerRestaurantOwner(@RequestBody @Valid RestaurantOwnerRegistrationDTO ownerRegistrationDTO){
            return authService.registerRestaurantOwner(ownerRegistrationDTO.getEmail(),ownerRegistrationDTO.getPassword());
        }
    }

    @RestController
    @RequestMapping("guest/authentication")
    public class AuthenticationController{

        @PostMapping("admin")
        public String loginAdmin(@RequestBody @Valid AdminSigninDTO adminSigninDTO){
            return authService.login(adminSigninDTO.getUsername(),adminSigninDTO.getPassword());
        }

        @PostMapping("customer")
        public String loginCustomer(@RequestBody @Valid CustomerSigninDTO customerSigninDTO){
            return authService.login(customerSigninDTO.getEmail(),customerSigninDTO.getPassword());
        }

        @PostMapping("restaurant-owner")
        public String loginRestaurantOwner(@RequestBody @Valid RestaurantOwnerSigninDTO restaurantOwnerSigninDTO){
            return authService.login(restaurantOwnerSigninDTO.getEmail(),restaurantOwnerSigninDTO.getPassword());
        }

    }

    @RestController
    @RequestMapping("guest/verification")
    public class VerificationController{
        @GetMapping("filter")
        public String verifyGuestFilter(){
            return "Guest access level";
        }

        @GetMapping("customer")
        public String verifyCustomerEmail(@RequestParam String token, @RequestParam String email){
            return authService.verifyCustomerEmail(token,email);
        }

        @GetMapping("restaurant-owner")
        public String verifyRestaurantOwnerEmail(@RequestParam String token, @RequestParam String email){
            return authService.verifyRestaurantOwnerEmail(token,email);
        }
    }
}
