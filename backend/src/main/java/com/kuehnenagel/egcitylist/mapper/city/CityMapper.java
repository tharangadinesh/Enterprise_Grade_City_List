package com.kuehnenagel.egcitylist.mapper.city;

import org.springframework.core.convert.converter.Converter;

import com.kuehnenagel.egcitylist.dto.city.CityDTO;
import com.kuehnenagel.egcitylist.model.city.City;

public class CityMapper extends GenericMapper<City, CityDTO> implements Converter<City, CityDTO> {

	private static CityMapper instance = null;

	private CityMapper() {
	}

	public static CityMapper getInstance() {
		if (instance == null) {
			instance = new CityMapper();
		}
		return instance;
	}

	@Override
	public CityDTO domainToDto(City domain) throws Exception {
		return convert(domain);
	}

	@Override
	public void dtoToDomain(CityDTO dto, City domain) throws Exception {
		domain.setId(dto.getId());
		domain.setName(dto.getName());
		domain.setPhoto( dto.getPhoto() );
		setCommanDomainFields(dto, domain);
	}

	@Override
	public CityDTO convert(City domain) {

		CityDTO dto = new CityDTO();

		dto.setId(domain.getId());
		dto.setName(domain.getName());
		dto.setPhoto(domain.getPhoto() );

		setCommanDTOFields(dto, domain);

		return dto;
	}

}
