package com.kuehnenagel.egcitylist.service.user.api;

import org.springframework.http.ResponseEntity;

import com.kuehnenagel.egcitylist.payload.request.SignupRequest;

public interface SignupService {

	ResponseEntity<?> signup(SignupRequest signupRequest);

}
