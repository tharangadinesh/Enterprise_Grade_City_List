package com.kuehnenagel.egcitylist.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kuehnenagel.egcitylist.model.user.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

}
