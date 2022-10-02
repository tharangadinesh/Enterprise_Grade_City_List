package com.kuehnenagel.egcitylist.service.user.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kuehnenagel.egcitylist.constant.user.UserRoles;
import com.kuehnenagel.egcitylist.dao.user.RoleDao;
import com.kuehnenagel.egcitylist.dao.user.UserDao;
import com.kuehnenagel.egcitylist.model.user.Role;
import com.kuehnenagel.egcitylist.model.user.User;
import com.kuehnenagel.egcitylist.model.user.UserRole;
import com.kuehnenagel.egcitylist.payload.request.SignupRequest;
import com.kuehnenagel.egcitylist.payload.response.SuccessMessageResponse;
import com.kuehnenagel.egcitylist.service.user.api.SignupService;

@Service
public class SignupServiceImpl implements SignupService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseEntity<?> signup(SignupRequest signUpRequest) {

		if (userDao.existsByUsername(signUpRequest.getUsername())) {
			throw new RuntimeException("Error: Username is already taken!");
		}

		User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();

		Set<UserRole> roles = createUserRoles(user, strRoles);

		user.setRoles(roles);
		userDao.save(user);

		return ResponseEntity.ok(new SuccessMessageResponse("User registered successfully!"));
	}

	private Set<UserRole> createUserRoles(User user, Set<String> strRoles) {
		Set<UserRole> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleDao.findByName(UserRoles.ROLE_ALLOW_VIEW)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add( new UserRole(user, userRole));
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ROLE_ALLOW_EDIT":
					Role adminRole = roleDao.findByName(UserRoles.ROLE_ALLOW_EDIT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(new UserRole(user, adminRole));

					break;
				default:
					Role userRole = roleDao.findByName(UserRoles.ROLE_ALLOW_VIEW)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(new UserRole(user, userRole));
				}
			});
		}
		return roles;
	}

}
