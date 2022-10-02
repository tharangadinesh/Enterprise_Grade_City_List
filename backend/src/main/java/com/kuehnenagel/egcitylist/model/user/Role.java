package com.kuehnenagel.egcitylist.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kuehnenagel.egcitylist.constant.user.UserRoles;
import com.kuehnenagel.egcitylist.model.BaseModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseModel {

	private static final long serialVersionUID = 4906478911928329688L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name="name")
	private UserRoles name;

	public Role(UserRoles name) {
		super();
		this.name = name;
	}

}
