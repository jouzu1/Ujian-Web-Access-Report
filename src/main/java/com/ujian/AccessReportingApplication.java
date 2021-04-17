package com.ujian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ujian.entity.AdminUser;
import com.ujian.entity.Korban;
import com.ujian.repository.AdminUserRepository;
import com.ujian.repository.KorbanRepository;

@SpringBootApplication
public class AccessReportingApplication implements CommandLineRunner{
	@Autowired
	KorbanRepository korbanInterface;
	
	@Autowired
	AdminUserRepository admRepo;

	public static void main(String[] args) {
		SpringApplication.run(AccessReportingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		AdminUser adm = new AdminUser();
		adm.setUsername("jo");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = "123";
		String encodedPassword = passwordEncoder.encode(plainPassword);
		adm.setPassword(encodedPassword);
		
		adm.setRole("user");
		this.admRepo.save(adm);
		
		
	}
	
	
}
