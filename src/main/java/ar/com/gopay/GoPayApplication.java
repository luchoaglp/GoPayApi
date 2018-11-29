package ar.com.gopay;

import ar.com.gopay.domain.Company;
import ar.com.gopay.domain.User;
import ar.com.gopay.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GoPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoPayApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository,
						   PasswordEncoder passwordEncoder) {

		return args -> {

			User company1 = new Company("despegar",
					"despegar@email.com",
					passwordEncoder.encode("123456"),
					"despegar.com",
					"https://www.staticontent.com/shifu/static/logos/despegar.svg");

			User company2 = new Company("potiers",
					"potiershome@email.com",
					passwordEncoder.encode("123456"),
					"Potiers Home",
					"https://www.potiershome.com/images/logo.png");

			userRepository.save(company1);
			userRepository.save(company2);

		};
	}

}

// Main source
// https://www.callicoder.com/spring-boot-spring-security-jwt-mysql-react-app-part-1/
