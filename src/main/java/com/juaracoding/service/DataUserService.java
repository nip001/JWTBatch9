package com.juaracoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juaracoding.entity.DataUser;
import com.juaracoding.repository.DataUserRepository;

@Service
public class DataUserService {

	@Autowired
	DataUserRepository duRepo;

	public DataUser getUserByUsername(String username) {
		return duRepo.findByUsername(username);
	}
	
	public List<DataUser> getAllUser() {
		return duRepo.findAll();
	}
	
	public String saveUser(DataUser user) {
		duRepo.save(user);
		return "Data Berhasil Terbuat";
	}
}
