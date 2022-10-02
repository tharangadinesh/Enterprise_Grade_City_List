package com.kuehnenagel.egcitylist.dto.city;

import com.kuehnenagel.egcitylist.dto.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO extends BaseDTO {

	private Integer id;
	private String name;
	private String photo;

	public CityDTO(String name, String photo) {
		super();
		this.name = name;
		this.photo = photo;
	}


}
