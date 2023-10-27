package com.nishant.FoodDelivery.main.model.user;


import com.nishant.FoodDelivery.main.model.restaurant.Restaurant;
import com.nishant.FoodDelivery.main.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant_owners")
public class RestaurantOwner extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @JoinColumn(unique = true)
    private String email;

    @Size(min = 10, max = 10)
    private String mobileNumber;

    @OneToMany
    private List<Restaurant> restaurants;
}
