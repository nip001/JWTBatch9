package com.juaracoding.service;

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
	
}
