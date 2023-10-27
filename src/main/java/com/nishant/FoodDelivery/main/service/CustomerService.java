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
import java.util.Set;
import java.util.stream.Collectors;

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

    public Customer loadCustomerFromToken(String token){
        String email = tokenService.getUsernameFromJwt(token.substring("Bearer ".length()));

        return (Customer) loadUserByUsername(email);
    }

    public String logoutCustomer(String token) {

        tokenService.blacklistToken(token.substring("Bearer ".length()));

        return "Success";
    }

    public String addAddress(String token, Address address) {

        Customer customer = loadCustomerFromToken(token);
        customer.getAddresses().add(address);

        customerRepo.save(customer);

        return "Success";
    }

    public List<Address> getAllUserAddresses(String token) {
        return loadCustomerFromToken(token).getAddresses();
    }

    public String deleteUserAddresses(String token, Set<Long> addressIds) {

        Customer customer = loadCustomerFromToken(token);

        customer.setAddresses(
                customer
                        .getAddresses()
                        .stream()
                        .filter(address -> !addressIds.contains(address.getId()))
                        .collect(Collectors.toList()));

        customerRepo.save(customer);

        return "Success";
    }
}
