package com.nishant.FoodDelivery.main.util;

import com.nishant.FoodDelivery.token.service.TokenBlacklistService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JWTBlacklistFilter implements Filter {

    private final TokenBlacklistService tokenBlacklistService;

    public JWTBlacklistFilter(TokenBlacklistService tokenBlacklistService) {
        this.tokenBlacklistService = tokenBlacklistService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if(request.getHeader("Authorization")!=null) {

            String token = request.getHeader("Authorization").substring("Bearer ".length());

            boolean isBlacklisted = tokenBlacklistService.isPresent(token);

            if (isBlacklisted) {
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization");
                return;
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
