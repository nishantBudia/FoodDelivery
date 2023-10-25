package com.nishant.FoodDelivery.main.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminSigninDTO {
    public String username;

    public String password;
}
