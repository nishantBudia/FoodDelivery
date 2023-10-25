package com.nishant.FoodDelivery.main.repo;

import com.nishant.FoodDelivery.main.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByUsername(String username);
}
