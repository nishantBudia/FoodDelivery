package com.nishant.FoodDelivery.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nishant.FoodDelivery.model.dto.TokenDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JWTBlacklistFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if(request.getHeader("Authorization")!=null) {

            String token = request.getHeader("Authorization").substring("Bearer ".length());

            URL url = new URL("http://localhost:8081/token/"+token);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            Integer responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder data = new StringBuilder();
            String readLine = null;

            while((readLine = in.readLine())!= null)
            {
                data.append(readLine);
            }

            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (data.toString().equals("true")) {
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization");
                return;
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
