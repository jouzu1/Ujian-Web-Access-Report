package com.ujian.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//import com.kuliah.main.entity.Mahasiswa;
//import com.kuliah.main.entity.Mahasiswa;
import com.ujian.entity.Korban;

public interface KorbanRepository extends CrudRepository<Korban, Long>{
//	@Query(value = "UPDATE korban SET status = 'Approve' WHERE id_user = ?1;",nativeQuery = true)
//	public Korban cari(long id);
	public Korban findByIdUser (Long id);
}
