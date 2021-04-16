package com.ujian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ujian.entity.Korban;
import com.ujian.repository.KorbanRepository;

@SpringBootApplication
public class AccessReportingApplication implements CommandLineRunner{
	@Autowired
	KorbanRepository korbanInterface;

	public static void main(String[] args) {
		SpringApplication.run(AccessReportingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		Korban korban1 = new Korban();
//		korban1.setNama("Jouzu");
//		korban1.setAlamat("Jalan jalan");
//		korban1.setKeterangan("Pelaku kabur");
//		this.korbanInterface.save(korban1);
	}
	
	
}
