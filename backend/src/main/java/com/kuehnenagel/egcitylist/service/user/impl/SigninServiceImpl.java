package com.kuehnenagel.egcitylist.service.user.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kuehnenagel.egcitylist.payload.request.SigninRequest;
import com.kuehnenagel.egcitylist.payload.response.UserInfoResponse;
import com.kuehnenagel.egcitylist.security.service.UserDetailsImpl;
import com.kuehnenagel.egcitylist.service.user.api.SigninService;
import com.kuehnenagel.egcitylist.util.JwtUtils;

@Service
public class SigninServiceImpl implements SigninService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Override
	public ResponseEntity<UserInfoResponse> signin(SigninRequest signinRequest) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity
				.ok()
				.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.body( new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), roles) );
	}

}
