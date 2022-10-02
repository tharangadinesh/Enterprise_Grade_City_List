package com.kuehnenagel.egcitylist.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SigninRequest {

	@NotBlank
	private String username;

	@NotBlank
	private String password;

}
