package ma.ens.security;

import ma.ens.security.entities.Role;
import ma.ens.security.entities.User;
import ma.ens.security.repositories.RoleRepository;
import ma.ens.security.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class SpringJwtApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtApiApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(
			RoleRepository roleRepository,
			UserRepository userRepository,
			PasswordEncoder passwordEncoder
	) {
		return args -> {
			Role roleUser = roleRepository.findByName("ROLE_USER")
					.orElseGet(() -> roleRepository.save(new Role(null, "ROLE_USER")));
			Role roleAdmin = roleRepository.findByName("ROLE_ADMIN")
					.orElseGet(() -> roleRepository.save(new Role(null, "ROLE_ADMIN")));

			userRepository.findByUsername("user")
					.orElseGet(() -> userRepository.save(new User(
							null,
							"user",
							passwordEncoder.encode("1234"),
							true,
							List.of(roleUser)
					)));

			userRepository.findByUsername("admin")
					.orElseGet(() -> userRepository.save(new User(
							null,
							"admin",
							passwordEncoder.encode("1234"),
							true,
							List.of(roleUser, roleAdmin)
					)));
		};
	}
}
