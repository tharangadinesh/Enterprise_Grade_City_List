package com.kuehnenagel.egcitylist.service.user.api;

import org.springframework.http.ResponseEntity;

import com.kuehnenagel.egcitylist.payload.response.SuccessMessageResponse;

public interface SignOutService {

	ResponseEntity<SuccessMessageResponse> signOut();
}
