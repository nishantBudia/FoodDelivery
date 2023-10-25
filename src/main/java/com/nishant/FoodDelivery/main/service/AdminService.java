package com.nishant.FoodDelivery.main.service;

import com.nishant.FoodDelivery.main.model.dto.TokenDTO;
import com.nishant.FoodDelivery.main.repo.AdminRepo;
import com.nishant.FoodDelivery.main.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;


@Service
public class AdminService implements UserDetailsService {
    @Autowired
    AdminRepo adminRepo;

    @Autowired
    TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("username not found"));
    }

    public String signoutAdmin(String token) {

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
