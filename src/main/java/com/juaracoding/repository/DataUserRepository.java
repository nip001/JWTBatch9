package com.juaracoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juaracoding.entity.DataUser;

public interface DataUserRepository extends JpaRepository<DataUser, Long>{
	DataUser findByUsername(String username);
}
