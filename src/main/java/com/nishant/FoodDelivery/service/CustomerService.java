package com.nishant.FoodDelivery.service;

import com.nishant.FoodDelivery.model.Customer;
import com.nishant.FoodDelivery.model.Role;
import com.nishant.FoodDelivery.model.dto.CustomerSignUpDTO;
import com.nishant.FoodDelivery.model.dto.TokenDTO;
import com.nishant.FoodDelivery.repo.CustomerRepo;
import com.nishant.FoodDelivery.repo.RoleRepo;
import com.nishant.FoodDelivery.util.HttpRequestUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("username not found"));
    }

    public String signoutCustomer(String token) {

        token = token.substring("Bearer ".length());

        try {
            return HttpRequestUtil
                    .postRequest(
                            Optional.of(new TokenDTO(token,tokenService.getTokenExpiryDate(token))),
                            "http://"+System.getenv("TOKEN_BLACKLIST_SERVER_URL"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
