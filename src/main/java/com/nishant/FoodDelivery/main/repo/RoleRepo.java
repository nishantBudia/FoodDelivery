package com.nishant.FoodDelivery.main.repo;


import com.nishant.FoodDelivery.main.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {
    public Role findByAuthority(String authority);
}
