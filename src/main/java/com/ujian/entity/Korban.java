package com.ujian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="korban")
public class Korban {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long idUser;
	private String nama;
	private String alamat;
	private String kejadian;
	private String keterangan;
	

	private String gambar;
	private String status;
}
