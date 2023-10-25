package com.nishant.FoodDelivery;

import com.nishant.FoodDelivery.model.Admin;
import com.nishant.FoodDelivery.model.Role;
import com.nishant.FoodDelivery.repo.AdminRepo;
import com.nishant.FoodDelivery.repo.CustomerRepo;
import com.nishant.FoodDelivery.repo.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class FoodDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryApplication.class, args);

	}

	@Bean
	CommandLineRunner run(RoleRepo roleRepo, CustomerRepo customerRepo, PasswordEncoder passwordEncode, AdminRepo adminRepo) {
		return args -> {
			Role adminRole = roleRepo.save(new Role("ADMIN"));
			roleRepo.save(new Role("CUSTOMER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			Admin admin = new Admin( "baba", passwordEncode.encode("password"), roles);

			adminRepo.save(admin);
		};
	}

}
