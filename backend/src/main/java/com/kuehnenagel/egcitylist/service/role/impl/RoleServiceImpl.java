package com.kuehnenagel.egcitylist.service.role.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kuehnenagel.egcitylist.constant.user.UserRoles;
import com.kuehnenagel.egcitylist.dao.user.RoleDao;
import com.kuehnenagel.egcitylist.model.user.Role;
import com.kuehnenagel.egcitylist.service.role.api.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private final static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void intRoles() {

		try {
			if ( roleDao.count() < 1 ) {

				List<Role> roles = Stream.of(
						new Role(UserRoles.ROLE_ALLOW_EDIT),
						new Role(UserRoles.ROLE_ALLOW_VIEW)
						).collect(Collectors.toList());

				roleDao.saveAll(roles);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error! While inserting initial User Roles, {}" , e.getMessage());
		}


	}

}
