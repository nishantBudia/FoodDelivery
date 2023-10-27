package com.nishant.FoodDelivery.main.service;

import com.nishant.FoodDelivery.main.repo.user.RestaurantOwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RestaurantOwnerService implements UserDetailsService {

    @Autowired
    RestaurantOwnerRepo restaurantOwnerRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return restaurantOwnerRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("email not found"));
    }
}
