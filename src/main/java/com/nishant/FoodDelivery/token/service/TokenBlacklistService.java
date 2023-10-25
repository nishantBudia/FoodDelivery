package com.nishant.FoodDelivery.token.service;

import com.nishant.FoodDelivery.token.model.Token;
import com.nishant.FoodDelivery.token.repo.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class TokenBlacklistService {
    @Autowired
    TokenRepo tokenRepo;

    public void addToken(String token, Instant tokenExpiryDate) {
        tokenRepo.save(new Token(token,tokenExpiryDate));
        if(tokenRepo.count()>=50_000){
            tokenRepo.deleteByExpiryDateLessThanEqual(Instant.now());
        }
    }

    public boolean isPresent(String token) {
        return tokenRepo.existsById(token);
    }
}
