package com.nishant.FoodDelivery.main.util;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JWTBlacklistFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if(request.getHeader("Authorization")!=null) {

            String token = request.getHeader("Authorization").substring("Bearer ".length());

            String url = "http://"+System.getenv("TOKEN_BLACKLIST_SERVER_URL")+"/"+token;

            String response = HttpRequestUtil.getRequest(url);

            if (response.equals("true")) {
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization");
                return;
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
