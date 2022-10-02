package com.kuehnenagel.egcitylist.constant.user;

import java.util.Arrays;
import java.util.List;

public enum UserRoles {

	ROLE_ALLOW_VIEW,
	ROLE_ALLOW_EDIT;

	public static List<UserRoles> getUserRoles(){

		return Arrays.asList(ROLE_ALLOW_VIEW, ROLE_ALLOW_EDIT);
	}

}
