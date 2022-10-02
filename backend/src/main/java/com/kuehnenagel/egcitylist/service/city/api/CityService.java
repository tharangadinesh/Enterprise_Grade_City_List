package com.kuehnenagel.egcitylist.service.city.api;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.kuehnenagel.egcitylist.dto.city.CityDTO;

public interface CityService {

	ResponseEntity<CityDTO> save(CityDTO dto);

	void saveAll(List<CityDTO> dtos);

	ResponseEntity<CityDTO> update(CityDTO dto);

	ResponseEntity<CityDTO> findById(Integer id);

	ResponseEntity<Map<String, Object>> findAll(Pageable pageable, String query);

	ResponseEntity<CityDTO> add(CityDTO dto) throws Exception;


}
