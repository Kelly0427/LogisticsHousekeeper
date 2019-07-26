package com.jy.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.jy.service.system.OperatorService;


@RestController
public class LoginController {
	@Autowired
	OperatorService operatorService;

	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("project/login");
	}
	
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public ModelAndView redirect(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();
		List<String> auth = new ArrayList<String>();
		for (GrantedAuthority grantedAuthority : authorities) {
			auth.add(grantedAuthority.getAuthority());
		}
		return new ModelAndView("redirect:/index");
	}
	@GetMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("project/content/repair");
	}
	
	@GetMapping("/record")
	public ModelAndView record() {
		return new ModelAndView("project/content/record");
	}
	
	@GetMapping("/records")
	public ModelAndView records() {
		return new ModelAndView("project/content/records");
	}
	@RequestMapping("*")
	public ModelAndView noPageToShow() {
		return new ModelAndView("redirect:/login");
	}
}
