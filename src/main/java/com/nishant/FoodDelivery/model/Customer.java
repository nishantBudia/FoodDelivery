package com.nishant.FoodDelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
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

    @OneToOne
    private Address address;

    public Customer(String username, String password, Collection<Role> authorities, String email, String mobileNumber, Address address) {
        super(username, password, authorities);
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    public Customer(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<Role> authorities, String email, String mobileNumber, Address address) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }
}
