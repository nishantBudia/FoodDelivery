package com.nishant.FoodDelivery.util;

public class MailingUtil {


    public static String getEmailMessage(String host,String email, String token){
        return "Account created successfully. Please click below link to verify account.\n\n" + getVerificationUrl(host,email,token);
    }

    public static String getVerificationUrl(String host,String email, String token){
        return host + "/guest/verification?token=" +token+"&email="+email;
    }
}
