package com.nishant.FoodDelivery.main.repo.user;

import com.nishant.FoodDelivery.main.model.user.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantOwnerRepo extends JpaRepository<RestaurantOwner,Long> {

    Optional<RestaurantOwner> findByUsername(String email);
}
