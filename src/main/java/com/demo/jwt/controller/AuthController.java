package com.demo.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.jwt.model.JwtRequest;
import com.demo.jwt.model.JwtResponse;
import com.demo.jwt.service.UserService;
import com.demo.jwt.utils.JwtUtility;

@RestController
public class AuthController {

	@Autowired
	private JwtUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String home() {
		return "Welcome from Parichay!!";
	}
	
	@GetMapping("/greet/{greeting}")
	public String greet(@PathVariable String greeting) {
		return greeting;
	}

	@PostMapping("/authenticate")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

		final String token = jwtUtility.generateToken(userDetails);

		return new JwtResponse(token);
	}
}