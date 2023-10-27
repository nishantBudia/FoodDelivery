package com.nishant.FoodDelivery;

import com.nishant.FoodDelivery.main.model.user.Admin;
import com.nishant.FoodDelivery.main.model.user.Role;
import com.nishant.FoodDelivery.main.repo.user.AdminRepo;
import com.nishant.FoodDelivery.main.repo.user.CustomerRepo;
import com.nishant.FoodDelivery.main.repo.user.RoleRepo;
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

			System.out.println(formatSize(Runtime.getRuntime().totalMemory()));
		};
	}

	public static String formatSize(long v) {
		if (v < 1024) return v + " B";
		int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
		return String.format("%.1f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
	}

}
