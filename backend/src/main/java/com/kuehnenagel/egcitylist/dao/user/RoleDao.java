package com.kuehnenagel.egcitylist.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kuehnenagel.egcitylist.constant.user.UserRoles;
import com.kuehnenagel.egcitylist.model.user.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(UserRoles name);

}
