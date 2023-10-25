package com.nishant.FoodDelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
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

    @Email
    @JoinColumn(unique = true)
    private String email;

    @Size(min = 10, max = 10)
    private String mobileNumber;

    @OneToMany
    private List<Address> addresses;

    public Customer(String username, String password, Set<Role> authorities, String email) {
        super(username, password, authorities);
        this.email = email;
    }
}
