package com.kuehnenagel.egcitylist.model.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.kuehnenagel.egcitylist.model.BaseModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class User extends BaseModel {

	private static final long serialVersionUID = 1430634898113857121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotBlank
	@Size(max = 255)
	@Column(name = "username")
	private String username;

	@NotBlank
	@Size(max = 255)
	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserRole> roles;

	public User(@NotBlank @Size(max = 255) String username, @NotBlank @Size(max = 255) String password) {
		super();
		this.username = username;
		this.password = password;
	}
}