package com.kuehnenagel.egcitylist.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponse {

	private Integer id;
	private String username;

	private List<String> roles;

}
