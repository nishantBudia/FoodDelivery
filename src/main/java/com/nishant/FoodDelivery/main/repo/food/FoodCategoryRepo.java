package com.nishant.FoodDelivery.main.repo.food;

import com.nishant.FoodDelivery.main.model.food.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCategoryRepo extends JpaRepository<FoodCategory,String> {
}
