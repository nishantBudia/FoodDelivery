package com.nishant.FoodDelivery.repo;


import com.nishant.FoodDelivery.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {
    public Role findByAuthority(String authority);
}
