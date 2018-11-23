package ar.com.gopay;

import ar.com.gopay.domain.Company;
import ar.com.gopay.domain.User;
import ar.com.gopay.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ImportResource(value="classpath:hsql_cfg.xml") // Only for hsqldb
public class GoPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoPayApplication.class, args);
	}

    @Bean
    CommandLineRunner init(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {

	    return args -> {

            User company1 = new Company("Company 1", "company1",
                    "company1@email.com",
                    passwordEncoder.encode("123456"));
            User company2 = new Company("Company 2", "company2",
                    "company2@email.com",
                    passwordEncoder.encode("654321"));

            userRepository.save(company1);
            userRepository.save(company2);
        };
    }

}

// Main source
// https://www.callicoder.com/spring-boot-spring-security-jwt-mysql-react-app-part-1/
