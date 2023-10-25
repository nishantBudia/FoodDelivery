package com.nishant.FoodDelivery.service;

import com.nishant.FoodDelivery.model.Customer;
import com.nishant.FoodDelivery.model.Role;
import com.nishant.FoodDelivery.model.dto.CustomerSignUpDTO;
import com.nishant.FoodDelivery.repo.CustomerRepo;
import com.nishant.FoodDelivery.repo.RoleRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    TokenService tokenService;

    @Autowired
    MailingService mailingService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("username not found"));
    }
}
