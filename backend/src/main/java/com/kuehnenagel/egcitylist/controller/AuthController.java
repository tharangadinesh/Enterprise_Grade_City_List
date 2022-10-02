package com.kuehnenagel.egcitylist.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuehnenagel.egcitylist.constant.user.UserRoles;
import com.kuehnenagel.egcitylist.payload.request.SigninRequest;
import com.kuehnenagel.egcitylist.payload.request.SignupRequest;
import com.kuehnenagel.egcitylist.service.user.api.SignOutService;
import com.kuehnenagel.egcitylist.service.user.api.SigninService;
import com.kuehnenagel.egcitylist.service.user.api.SignupService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", maxAge = 3600)
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private SigninService signinService;

	@Autowired
	private SignupService signupService;

	@Autowired
	private SignOutService signOutService;

	@PostMapping("signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequest signinRequest) {

		return signinService.signin(signinRequest);
	}

	@PostMapping("signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

		return signupService.signup(signupRequest);
	}

	@PostMapping("signout")
	public ResponseEntity<?> logoutUser() {

		return signOutService.signOut();
	}

	@GetMapping("roles")
	public ResponseEntity<?> getUserLoges() {

		return ResponseEntity.ok(UserRoles.getUserRoles());
	}


}
