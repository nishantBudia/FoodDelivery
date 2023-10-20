package com.nishant.FoodDelivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Map;
import java.util.Properties;

@Configuration
public class MailingConfiguration {

    private final String email = System.getenv("AUTO_MAIL_SENDER_ADDRESS");
    private final String password = System.getenv("AUTO_MAIL_SENDER_PASSWORD");
    @Bean
    public JavaMailSender mailSender(){

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(465);
        Properties prop = mailSender.getJavaMailProperties();
        prop.put("mail.smtp.ssl.enable","true");
        //System.out.println(prop);
        mailSender.setJavaMailProperties(prop);
        mailSender.setUsername(email);
        mailSender.setPassword(password);

        return mailSender;
    }
}
