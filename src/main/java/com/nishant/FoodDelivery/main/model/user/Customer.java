package com.nishant.FoodDelivery.main.model.user;

import com.nishant.FoodDelivery.main.model.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 10, max = 10)
    private String mobileNumber;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Address> addresses;

    public Customer(String username, String password, Set<Role> authorities) {
        super(username, password, authorities);
    }
}
