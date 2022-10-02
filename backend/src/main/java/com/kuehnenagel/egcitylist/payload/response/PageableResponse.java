package com.kuehnenagel.egcitylist.payload.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

public class PageableResponse {

	Map<String, Object> response = new HashMap<>();

	public Map<String, Object> parse(List<?> dtos, Page<?> page){

		response.put("items", dtos);
		response.put("currentPage", page.getNumber());
		response.put("totalItems", page.getTotalElements());
		response.put("totalPages", page.getTotalPages());

		return response;
	}

	public Map<String, Object> empty(){

		response.put("items", 0);
		response.put("currentPage", 0);
		response.put("totalItems", 0);
		response.put("totalPages",0);

		return response;
	}
}
