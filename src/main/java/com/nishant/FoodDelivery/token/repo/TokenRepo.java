package com.nishant.FoodDelivery.token.repo;

import com.nishant.FoodDelivery.token.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface TokenRepo extends JpaRepository<Token, String> {
    long deleteByExpiryDateLessThanEqual(Instant expiryDate);
}
