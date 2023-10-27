package com.nishant.FoodDelivery.main.repo.user;

import com.nishant.FoodDelivery.main.model.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Optional<Customer> findByUsername(String username);
}
