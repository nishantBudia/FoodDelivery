package com.nishant.FoodDelivery.main.service;

import com.nishant.FoodDelivery.main.util.HttpRequestUtil;
import com.nishant.FoodDelivery.main.model.dto.TokenDTO;
import com.nishant.FoodDelivery.main.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

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
