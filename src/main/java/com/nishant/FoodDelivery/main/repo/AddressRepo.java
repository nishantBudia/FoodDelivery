package com.nishant.FoodDelivery.main.repo;

import com.nishant.FoodDelivery.main.model.Address;
import com.nishant.FoodDelivery.main.model.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {

}
