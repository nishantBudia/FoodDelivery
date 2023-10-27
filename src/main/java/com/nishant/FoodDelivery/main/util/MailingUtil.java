package com.nishant.FoodDelivery.main.util;

public class MailingUtil {

    public static String getRestaurantOwnerVerificationEmailMessage(String host,String email, String token){
        return "Account created successfully. Please click below link to verify account.\n\n" + getCustomerVerificationUrl(host,email,token);
    }

    public static String getRestaurantOwnerVerificationUrl(String host,String email, String token){
        return host + "/guest/restaurant-owner/verification?token=" +token+"&email="+email;
    }

    public static String getCustomerVerificationEmailMessage(String host,String email, String token){
        return "Account created successfully. Please click below link to verify account.\n\n" + getCustomerVerificationUrl(host,email,token);
    }

    public static String getCustomerVerificationUrl(String host,String email, String token){
        return host + "/guest/customer/verification?token=" +token+"&email="+email;
    }
}
