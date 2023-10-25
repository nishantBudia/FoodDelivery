package com.nishant.FoodDelivery.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenService {
    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;

    public String generateJwt(Authentication auth){

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt((Instant.now()))
                .expiresAt(Instant.now().plus(30, ChronoUnit.DAYS))
                .subject(auth.getName())
                .claim("roles",scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateJwt(String username){
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt((Instant.now()))
                .expiresAt(Instant.now().plus(10, ChronoUnit.MINUTES))
                .subject(username)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String getUsernameFromJwt( String token){
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject();
    }

    public Instant getTokenExpiryDate(String token){
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getExpiresAt();
    }
}
