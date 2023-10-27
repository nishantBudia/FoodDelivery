package com.nishant.FoodDelivery.main.model.user;

import com.nishant.FoodDelivery.main.model.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
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

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public Customer(String username, String password, Set<Role> authorities) {
        super(username, password, authorities);
    }

    public void setAddresses(List<Address> addresses){
        this.addresses.removeAll(this.addresses);
        this.addresses.addAll(addresses);
    }
}
