package com.nishant.FoodDelivery.main.model.food;

import com.nishant.FoodDelivery.main.model.food.FoodCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public class FoodItem {
    @NotBlank
    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private FoodCategory category;

    @Enumerated(value = EnumType.STRING)
    private FoodType type;   //veg, non veg, etc

    @NotNull
    private Double price;

    private String photoUrl;
}
