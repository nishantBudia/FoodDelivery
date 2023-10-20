package com.nishant.FoodDelivery.service;

import com.nishant.FoodDelivery.model.dto.CustomerSignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GuestService {
    @Autowired
    CustomerService customerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String registerCustomer(CustomerSignUpDTO customerSignUpDTO) {
        return customerService.registerCustomer(
                customerSignUpDTO.getUsername(),
                customerSignUpDTO.getEmail(),
                passwordEncoder.encode(customerSignUpDTO.getPassword()));
    }
}
