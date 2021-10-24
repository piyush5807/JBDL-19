package com.example.jbdl.demosecurityjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoSecurityJpaApplication implements CommandLineRunner {

	@Autowired
	AppRepository appRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoSecurityJpaApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		AppUser appUser = AppUser.builder()
//				.username(passwordEncoder.encode("jim"))
				.username("jim123")
				.password(passwordEncoder.encode("jim123"))
				.authorities("STUDENT")
				.build();

		appRepository.save(appUser);
	}
}
