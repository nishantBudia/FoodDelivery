package com.nishant.FoodDelivery.main.service;

import com.nishant.FoodDelivery.main.model.Address;
import com.nishant.FoodDelivery.main.model.user.Customer;
import com.nishant.FoodDelivery.main.repo.AddressRepo;
import com.nishant.FoodDelivery.main.repo.user.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    TokenService tokenService;

    @Autowired
    AddressRepo addressRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerRepo.findByUsername(email).orElseThrow(()->new UsernameNotFoundException("username not found"));
    }

    public String logoutCustomer(String token) {

        tokenService.blacklistToken(token.substring("Bearer ".length()));

        return "Success";
    }

    public String addAddress(String token, Address address) {
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);

        String email = tokenService.getUsernameFromJwt(token.substring("Bearer ".length()));

        Customer customer = (Customer) loadUserByUsername(email);
        customer.setAddresses(addresses);

        customerRepo.save(customer);

        return "Success";
    }

    public List<Address> getAllUserAddresses(String token) {
        return ((Customer)loadUserByUsername(
                tokenService.getUsernameFromJwt(
                        token.substring("Bearer ".length()))))
                .getAddresses();
    }
}
