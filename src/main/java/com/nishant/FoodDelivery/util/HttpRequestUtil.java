package com.nishant.FoodDelivery.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nishant.FoodDelivery.model.dto.TokenDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class HttpRequestUtil {
    public static String getRequest(String urlString) throws IOException {

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException("bad url");
        }

        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

        return data.toString();
    }

    public static String postRequest(Optional<? extends Object> requestBody, String urlString) throws IOException{


        URL url = new URL(urlString);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type","application/json");

        con.setRequestProperty("Accept","application/json");

        con.setDoOutput(true);

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        objectMapper.registerModule(new JavaTimeModule());

        String body = objectMapper.writeValueAsString(requestBody.get());

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = body.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }
}
