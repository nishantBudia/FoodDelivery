package com.nishant.FoodDelivery.main.service;

import com.nishant.FoodDelivery.main.model.user.Customer;
import com.nishant.FoodDelivery.main.model.user.Role;
import com.nishant.FoodDelivery.main.repo.user.CustomerRepo;
import com.nishant.FoodDelivery.main.repo.user.RoleRepo;
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

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    JwtDecoder jwtDecoder;

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

    public String verifyEmail(String token, String email) {
        Jwt jwt = jwtDecoder.decode(token);

        Customer customer = customerRepo.findByUsername(email).orElseThrow(()->new UsernameNotFoundException("username not found"));

        if(!jwt.getSubject().equals(customer.getUsername())){
            return "Invalid Authorization";
        }

        customer.enable();

        customerRepo.save(customer);

        return "Verification success";
    }


    public String registerCustomer(String email, String password) {

        Set<Role> authorities = new HashSet<>();
        authorities.add(roleRepo.findByAuthority("CUSTOMER"));

        Customer customer = customerRepo.save(new Customer(
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
