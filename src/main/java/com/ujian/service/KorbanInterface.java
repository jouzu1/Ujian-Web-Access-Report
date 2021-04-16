package com.ujian.service;

import java.util.List;


import com.ujian.entity.Korban;

public interface KorbanInterface {
	public List<Korban> getKorban();
	public Korban getKorbanByName(String name);
	
	public Korban addKorban(Korban korban);
	public Korban getKorbanById(String id);
	public void deleteKorban(String id);
}
