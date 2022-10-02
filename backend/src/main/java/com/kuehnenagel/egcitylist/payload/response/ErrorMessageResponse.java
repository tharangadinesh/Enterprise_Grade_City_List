package com.kuehnenagel.egcitylist.payload.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageResponse {

	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;

}
