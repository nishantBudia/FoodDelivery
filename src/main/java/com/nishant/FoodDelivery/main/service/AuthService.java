package com.nishant.FoodDelivery.main.service;

import com.nishant.FoodDelivery.main.model.dto.RestaurantOwnerRegistrationDTO;
import com.nishant.FoodDelivery.main.model.user.Customer;
import com.nishant.FoodDelivery.main.model.user.RestaurantOwner;
import com.nishant.FoodDelivery.main.model.user.Role;
import com.nishant.FoodDelivery.main.repo.user.CustomerRepo;
import com.nishant.FoodDelivery.main.repo.user.RestaurantOwnerRepo;
import com.nishant.FoodDelivery.main.repo.user.RoleRepo;
import com.nishant.FoodDelivery.main.util.JWTFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @Autowired
    MailingService mailingService;

    @Autowired
    AuthenticationManager authenticationManager;

    public String verifyCustomerEmail(String token, String email) {
        Customer customer = (Customer) customerService.loadUserByUsername(email);

        if(!tokenService.getUsernameFromJwt(token).equals(customer.getUsername())
                ||tokenService.getTokenFunction(token)!= JWTFunctions.VERIFICATION)
        {
            return "Invalid Authorization";
        }

        if(tokenService.getTokenExpiryDate(token).compareTo(Instant.now())>0){
            return "Expired Token";
        }

        customer.enable();

        customerService.customerRepo.save(customer);

        return "Verification success";
    }


    public String registerCustomer(String email, String password) {

        Set<Role> authorities = new HashSet<>();
        authorities.add(roleRepo.findByAuthority("CUSTOMER"));

        Customer customer = customerService.customerRepo.save(new Customer(
                email,
                passwordEncoder.encode(password),
                authorities));

        String token = tokenService.generateJwt(customer.getUsername());

        mailingService.sendVerificationEmail(email,token);

        return "Success";
    }

    public String loginCustomer(String email, String password) {

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,password)
            );

            return tokenService.generateJwt(auth,email);
        }catch (AuthenticationException e){
            return "invalid credentials";
        }

    }

    public String loginAdmin(String username, String password) {
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );

            return tokenService.generateJwt(auth,username);
        }catch (AuthenticationException e){
            return "invalid credentials";
        }

    }

}
