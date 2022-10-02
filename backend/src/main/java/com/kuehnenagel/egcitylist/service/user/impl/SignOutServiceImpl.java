package com.kuehnenagel.egcitylist.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kuehnenagel.egcitylist.payload.response.SuccessMessageResponse;
import com.kuehnenagel.egcitylist.service.user.api.SignOutService;
import com.kuehnenagel.egcitylist.util.JwtUtils;

@Service
public class SignOutServiceImpl implements SignOutService {

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public ResponseEntity<SuccessMessageResponse> signOut() {

		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new SuccessMessageResponse("You've been signed out!"));

	}

}
