package com.nishant.FoodDelivery.main.service;

import com.nishant.FoodDelivery.main.model.food.FoodCategory;
import com.nishant.FoodDelivery.main.repo.user.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminService implements UserDetailsService {
    @Autowired
    AdminRepo adminRepo;

    @Autowired
    TokenService tokenService;

    @Autowired
    FoodCategoryService

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("username not found"));
    }

    public String logoutAdmin(String token) {

        token = token.substring("Bearer ".length());

        tokenService.blacklistToken(token);

        return "Success";
    }

    public List<FoodCategory> addFoodCategories(List<FoodCategory> foodCategories) {
    }
}
