package com.juaracoding.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.config.JwtTokenUtil;
import com.juaracoding.entity.Berita;
import com.juaracoding.service.BeritaService;

@RestController
@RequestMapping("/berita")
public class BeritaController {

	@Autowired
	BeritaService beritaSvc;
	
	@Autowired
	JwtTokenUtil tokenUtil;
	
	@GetMapping("/")
	public List<Berita> getAll(){
		return beritaSvc.getAllBerita();
	}
	
	@GetMapping("/{title}")
	public List<Berita> getBeritaByTitle(@PathVariable String title){
		return beritaSvc.getByTitle(title);
	}
	
	@PostMapping("/save")
	public String saveBerita(@RequestBody Berita berita, @RequestHeader("Authorization") String header) {
		String username = tokenUtil.getUsernameFromToken(header.substring(7));
		berita.setAuthor(username);
		Date tanggal = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		berita.setTanggal(formatter.format(tanggal));
		
		return this.beritaSvc.saveBerita(berita);
	}
	
}
