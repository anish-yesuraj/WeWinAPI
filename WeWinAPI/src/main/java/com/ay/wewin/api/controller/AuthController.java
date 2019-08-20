package com.ay.wewin.api.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ay.wewin.api.message.request.LoginDTO;
import com.ay.wewin.api.message.request.SignUpDTO;
import com.ay.wewin.api.message.response.JwtResponse;
import com.ay.wewin.api.message.response.ResponseMessage;
import com.ay.wewin.api.model.Role;
import com.ay.wewin.api.model.RoleName;
import com.ay.wewin.api.model.User;
import com.ay.wewin.api.repository.IRoleRepository;
import com.ay.wewin.api.repository.IUserRepository;
import com.ay.wewin.api.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	Logger log = LoggerFactory.getLogger(AuthController.class);
			
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IRoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDTO signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			log.info("AYRES400 >> Username already exists : "+signUpRequest.getUsername());
			return new ResponseEntity<>(new ResponseMessage("AYRES400 $ Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			log.info("AYRES400 >> Email is already in use : "+signUpRequest.getEmail());
			return new ResponseEntity<>(new ResponseMessage("AYRES400 $ Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), 
								signUpRequest.getFirstname(), signUpRequest.getLastname(), 
								signUpRequest.getEmail(), signUpRequest.getMobile());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			
			Role userRole = roleRepository.findByName(RoleName.valueOf(role))
									.orElseThrow(() -> {
										log.info("AYRES400 >> User Role not found : "+role);
										return new RuntimeException("AYRES400 $ User Role not found.");
									});
			roles.add(userRole);
		});

		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("AYRES200 $ User registered successfully!"), HttpStatus.OK);
	}
}
