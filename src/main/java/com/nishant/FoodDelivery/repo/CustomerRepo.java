package com.nishant.FoodDelivery.repo;

import com.nishant.FoodDelivery.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByUsername(String username);
}
