package com.kuehnenagel.egcitylist.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = -5356423279919704668L;

	@Column(name = "is_deleted", nullable = false)
	protected Boolean isDeleted = false;

	@Version
	@Column(name = "version")
	protected Integer version;

}
