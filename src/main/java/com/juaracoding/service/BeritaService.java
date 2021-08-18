package com.juaracoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juaracoding.entity.Berita;
import com.juaracoding.repository.BeritaRepository;

@Service
public class BeritaService {

	@Autowired
	BeritaRepository beritaRepo;
	
	public String saveBerita(Berita berita) {
		this.beritaRepo.save(berita);
		return "Data berhasil disimpan";
	}
	
	public List<Berita> getAllBerita(){
		return this.beritaRepo.findAll();
	}
	
	public List<Berita> getByTitle(String title){
		return this.beritaRepo.findBeritaByTitle(title);
	}
}
