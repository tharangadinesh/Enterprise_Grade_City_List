package com.kuehnenagel.egcitylist.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityPageableRequest {

	private String query;
	private int page;
	private int size;

}
