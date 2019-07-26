package com.jy.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jy.domain.system.Operator;
import com.jy.domain.system.OperatorRepository;
import com.jy.service.login.CustomUserService;

@Service
public class OperatorServiceImpl implements OperatorService{
	@Autowired
	OperatorRepository operatorRepository;
	@Autowired
	CustomUserService customUserService;
	//注册
	@Override
	public Operator addOperator(Operator operator) {
		return operatorRepository.save(operator);
	}
	//获取当前用户
	@Override
	public String operatorRealName() {
			 return ((Operator) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealName();
	}
	//获取当前用户
	@Override
	public String operatorName() {
		 return ((Operator) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();
	}
	//获取当前用户手机号码
	@Override
	public String operatorPhone() {
		return ((Operator) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPhone();
	}
	//获取当前用户身份
	@Override
	public String operatorRole() {
		return ((Operator) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRole();
	}
	

}
