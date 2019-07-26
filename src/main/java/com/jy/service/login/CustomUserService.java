package com.jy.service.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.jy.domain.system.Operator;
import com.jy.domain.system.OperatorRepository;


@Service
public class CustomUserService implements UserDetailsService {
	@Autowired
	OperatorRepository operatorRepository;
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserDetails loadUserByUsername(String name) throws InternalAuthenticationServiceException {
		Operator user = operatorRepository.findByName(name);
		if (user == null) {
			throw new InternalAuthenticationServiceException("用户名不存在");
		}
		return user;
	}
}
