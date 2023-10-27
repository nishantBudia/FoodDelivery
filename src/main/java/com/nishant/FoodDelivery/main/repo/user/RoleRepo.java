package com.nishant.FoodDelivery.main.repo.user;


import com.nishant.FoodDelivery.main.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {
    public Role findByAuthority(String authority);
}
