package com.kuehnenagel.egcitylist.service.city.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kuehnenagel.egcitylist.dao.city.CityDao;
import com.kuehnenagel.egcitylist.dto.city.CityDTO;
import com.kuehnenagel.egcitylist.exception.ResourceNotFoundException;
import com.kuehnenagel.egcitylist.mapper.city.CityMapper;
import com.kuehnenagel.egcitylist.model.city.City;
import com.kuehnenagel.egcitylist.payload.response.PageableResponse;
import com.kuehnenagel.egcitylist.service.city.api.CityService;

@Service
public class CityServiceImpl implements CityService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CityDao cityDao;

	@Override
	public ResponseEntity<CityDTO> add(CityDTO dto) throws Exception {

		City domain =  new City();

		CityMapper.getInstance().dtoToDomain(dto, domain);

		cityDao.save(domain);

		return ResponseEntity.ok(CityMapper.getInstance().domainToDto(domain));

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseEntity<CityDTO> save(CityDTO dto) {
		try {

			City city = new City();

			CityMapper.getInstance().dtoToDomain(dto, city);

			save(dto, city);

			return new ResponseEntity<CityDTO>(CityMapper.getInstance().domainToDto(city), HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Error! Couldn't save city {}", e.getMessage());
		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveAll(List<CityDTO> dtos) {
		try {

			if ( cityDao.count() < 1) {

				for (CityDTO cityDTO : dtos) {

					save(cityDTO,  new City());
				}
			}

		} catch (Exception e) {
			logger.error("Error occured! {}", e.getMessage());
			e.printStackTrace();
		}
	}

	private void save(CityDTO cityDTO, City city) throws Exception {
		CityMapper.getInstance().dtoToDomain(cityDTO, city);
		cityDao.save(city);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseEntity<CityDTO> update(CityDTO dto) {

		try {

			City city = cityDao.findById(dto.getId())
					.orElseThrow( () -> new ResourceNotFoundException("Not found City with id = " + dto.getId()));

			CityMapper.getInstance().dtoToDomain(dto, city);

			cityDao.save(city);

			return ResponseEntity.ok().body(CityMapper.getInstance().domainToDto(city));

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error! Couldn't update city, {}" , e.getMessage());
		}

		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

	}

	@Override
	public ResponseEntity<CityDTO> findById(Integer id) {

		try {

			City city = cityDao.findById(id)
					.orElseThrow( () -> new ResourceNotFoundException("Not found City with id = " + id));

			return ResponseEntity.ok().body(CityMapper.getInstance().domainToDto(city));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error! Couldn't find city, {}" , e.getMessage());
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@Override
	public ResponseEntity<Map<String, Object>> findAll(Pageable pageable, String search) {

		try {

			Page<City> cities = search == null ? cityDao.findAll(pageable) : cityDao.findByNameContaining(search, pageable);

			return ResponseEntity.ok().body( CityMapper.getInstance().pageToPageableResponse(cities) );

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error! Couldn't retrieved, {}" , e.getMessage());
		}

		return ResponseEntity.ok().body(new PageableResponse().empty());

	}

}
