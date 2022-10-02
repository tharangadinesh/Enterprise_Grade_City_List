package com.kuehnenagel.egcitylist.dto;

import lombok.Data;

@Data
public class BaseDTO {

	private Integer id;
	private Integer version;

	private Boolean isDeleted = false;

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted == null ? Boolean.FALSE : isDeleted;
	}

}
