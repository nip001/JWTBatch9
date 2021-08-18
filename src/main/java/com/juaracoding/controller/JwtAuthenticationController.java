package com.juaracoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.config.JwtTokenUtil;
import com.juaracoding.entity.DataUser;
import com.juaracoding.service.DataUserService;
import com.juaracoding.service.MyUserDetails;

@RestController
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil tokenUtil;

	@Autowired
	private MyUserDetails uService;
	
	@Autowired
	private DataUserService duService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody DataUser dataUser) throws Exception{
		authenticate(dataUser.getUsername(), dataUser.getPassword());
		
		final UserDetails userDetails = uService.loadUserByUsername(dataUser.getUsername());
		final String token = tokenUtil.generateToken(userDetails, duService.getUserByUsername(dataUser.getUsername()).getRole());

		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody DataUser dataUser){
		BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();
		dataUser.setPassword(passwordEncode.encode(dataUser.getPassword()));
		return ResponseEntity.ok(duService.saveUser(dataUser));
	}
	
	private void authenticate(String username, String password) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			// TODO: handle exception
			throw new Exception("USER_DISABLED",e);
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception("INVALID_CREDENTIALS",e);
		}
	}
	
}
