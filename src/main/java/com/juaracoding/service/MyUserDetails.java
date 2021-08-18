package com.juaracoding.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.juaracoding.entity.DataUser;
import com.juaracoding.repository.DataUserRepository;

@Service
public class MyUserDetails implements UserDetailsService{
	@Autowired
	DataUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<SimpleGrantedAuthority> roles = null;
		DataUser user = userRepo.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User tidak ditemukan dengan username : "+username);
		}
		
		roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
		
		return new User(user.getUsername(),user.getPassword(), roles);
		
	}

}
