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
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant_owners")
public class RestaurantOwner extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 10, max = 10)
    private String mobileNumber;

    @OneToMany
    private List<Restaurant> restaurants;

    public RestaurantOwner(String username, String password, Set<Role> authorities) {
        super(username, password, authorities);
    }
}
