package com.kuehnenagel.egcitylist.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kuehnenagel.egcitylist.model.BaseModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole extends BaseModel {

	private static final long serialVersionUID = 7226076663279000905L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(targetEntity = Role.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	public UserRole(User user, Role role) {
		super();
		this.user = user;
		this.role = role;
	}

}