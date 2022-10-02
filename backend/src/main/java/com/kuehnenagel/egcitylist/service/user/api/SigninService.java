package com.kuehnenagel.egcitylist.service.user.api;

import org.springframework.http.ResponseEntity;

import com.kuehnenagel.egcitylist.payload.request.SigninRequest;
import com.kuehnenagel.egcitylist.payload.response.UserInfoResponse;

public interface SigninService {

	ResponseEntity<UserInfoResponse> signin(SigninRequest signinRequest);

}
