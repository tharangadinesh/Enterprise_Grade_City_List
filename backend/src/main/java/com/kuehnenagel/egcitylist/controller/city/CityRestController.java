package com.kuehnenagel.egcitylist.controller.city;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuehnenagel.egcitylist.dto.city.CityDTO;
import com.kuehnenagel.egcitylist.payload.request.CityPageableRequest;
import com.kuehnenagel.egcitylist.service.city.api.CityService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", maxAge = 3600)
@RequestMapping(CityRestController.CITY_REST_API)
public class CityRestController {

	public static final String CITY_REST_API = "/api/v1/city/";

	@Autowired
	CityService cityService;

	@GetMapping("all")
	public ResponseEntity<Map<String, Object>> getCities(CityPageableRequest request) {

		return cityService.findAll( PageRequest.of(request.getPage(), request.getSize()) , request.getQuery());

	}

	@GetMapping("{id}")
	public ResponseEntity<CityDTO> getCityById(@PathVariable("id")Integer id) {

		return cityService.findById(id);

	}

	@PostMapping("add")
	public ResponseEntity<CityDTO> add(@Valid @RequestBody CityDTO dto) throws Exception {

		return cityService.add(dto);

	}

	@PutMapping("update")
	public ResponseEntity<CityDTO> update(@Valid @RequestBody CityDTO dto) {

		return cityService.update(dto);

	}

}
