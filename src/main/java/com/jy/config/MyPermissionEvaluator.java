package com.jy.config;

import java.io.Serializable;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.jy.domain.system.Operator;
//import com.jy.domain.system.Role;

@Configuration
public class MyPermissionEvaluator implements PermissionEvaluator {

	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		Operator user = (Operator) authentication.getPrincipal();
//		for (Role role : user.getRoles()) {
//			for (Permission permis : role.getSysPermission()) {
//				if (permis.getPermission().equals(permission.toString())) {
//					return true;
//				}
//			}
//		}
		return true;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		return false;
	}

}
