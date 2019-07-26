package com.jy.web.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jy.domain.system.Operator;
import com.jy.service.system.OperatorService;



@RestController
public class OperatorController {
	@Autowired
	OperatorService operatorService;
	
	/*
	 * 注册接口
	 */
	@PostMapping("/system/addOperator")
	public Operator addOperator(Operator operator) {
		return operatorService.addOperator(operator);
	}
    //注册视图
	@GetMapping("/signup")
	public ModelAndView signup() {
		return new ModelAndView("project/content/signup");
	}
	
	//获取当前用户
	@GetMapping("/operatorName")
	public String operatorName() {
		return operatorService.operatorName();
	}
	
	//获取当前用户
	@GetMapping("/operatorPhone")
	public String operatorPhone() {
		return operatorService.operatorPhone();
	}
	
	//获取当前用户身份
	@GetMapping("/operatorRole")
	public String operatorRole() {
		return operatorService.operatorRole();
	}
}
