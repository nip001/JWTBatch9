package com.juaracoding.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name= "berita")
public class Berita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String deskripsi;
	private String tanggal;
	private String author;
}
