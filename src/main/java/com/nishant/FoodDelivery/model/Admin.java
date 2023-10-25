package com.nishant.FoodDelivery.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@NoArgsConstructor
@Table(name = "admins")
public class Admin extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    public Admin(String username, String password, Set<Role> authorities) {
        super(username, password, authorities);
        super.enable();
    }
}
