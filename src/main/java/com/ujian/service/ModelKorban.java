package com.ujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ujian.entity.Korban;
import com.ujian.repository.KorbanRepository;

@Service
public class ModelKorban implements KorbanInterface{

	@Autowired
	KorbanRepository korbanRepo;
	
	@Override
	public List<Korban> getKorban() {
		// TODO Auto-generated method stub
		return (List<Korban>) this.korbanRepo.findAll();
	}

	@Override
	public Korban getKorbanByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Korban addKorban(Korban korban) {
		// TODO Auto-generated method stub
		return this.korbanRepo.save(korban);
	}

	@Override
	public Korban getKorbanById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteKorban(String id) {
		// TODO Auto-generated method stub
		
	}
}