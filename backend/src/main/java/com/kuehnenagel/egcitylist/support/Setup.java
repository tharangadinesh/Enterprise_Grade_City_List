package com.kuehnenagel.egcitylist.support;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kuehnenagel.egcitylist.dto.city.CityDTO;
import com.kuehnenagel.egcitylist.service.city.api.CityService;
import com.kuehnenagel.egcitylist.service.role.api.RoleService;
import com.kuehnenagel.egcitylist.util.CsvUtil;

@Component
public class Setup {

	@Autowired
	private CsvUtil csvUtil;

	@Autowired
	private CityService cityService;

	@Autowired
	private RoleService roleService;

	@PostConstruct
	private void setupData() {

		List<CityDTO> list = csvUtil.loadObjectList(CityDTO.class, "cities.csv");

		cityService.saveAll(list);

		roleService.intRoles();

	}

}