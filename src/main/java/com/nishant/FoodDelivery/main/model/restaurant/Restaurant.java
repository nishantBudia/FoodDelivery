package com.nishant.FoodDelivery.main.model.restaurant;

import com.nishant.FoodDelivery.main.model.Address;
import com.nishant.FoodDelivery.main.model.user.RestaurantOwner;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    private String name;

    @Email
    private String email;

    private String description;

    @Size(min = 10, max = 10)
    private String phoneNumber;

    private String photoUrl;

    private Boolean enabled;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Address address;

    @ManyToMany
    private List<Cuisine> cuisines;

    @OneToOne(fetch = FetchType.LAZY)
    private Menu menu;


}
