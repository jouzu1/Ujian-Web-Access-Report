package com.ujian.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.juaracoding.jpa.entity.Product;
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
//		return this.korbanRepo.findByIdKorban(Long.parseLong(id));
		return korbanRepo.findByIdUser(Long.parseLong(id));
	}

	@Override
	public void deleteKorban(String id) {
		// TODO Auto-generated method stub
		
	}
	
	public void save(Korban updateStatus) {
        // TODO Auto-generated method stub
		this.korbanRepo.save(updateStatus);
//        this.laporanRepo.save(updateStatus);
    }

	@Override
	public Korban cariApprove() {
		// TODO Auto-generated method stub
		return this.korbanRepo.findApprove();
	}

	@Override
	public Korban cariProses() {
		// TODO Auto-generated method stub
		return this.korbanRepo.findProccess();
	}
}
