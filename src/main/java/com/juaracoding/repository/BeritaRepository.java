package com.juaracoding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.juaracoding.entity.Berita;

public interface BeritaRepository extends JpaRepository<Berita, Long>{
	@Query(value="Select * from berita where title LIKE %?1%",nativeQuery = true)
	List<Berita> findBeritaByTitle(String title);
}
