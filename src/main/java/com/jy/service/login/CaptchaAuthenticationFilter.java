package com.jy.service.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



public final class CaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		Cookie cookie = new Cookie("jy_innerUrl", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		 String requestCaptcha = "haha";
		 
		 String requestFlag=request.getParameter("flag");
		 request.getSession().setAttribute("flag", "1");
		 
		 String resultCaptcha;
		 if (request.getSession().getAttribute("captcha") != null) {
		 resultCaptcha ="haha";
		 request.getSession().getAttribute("captcha").toString();
//		 if (null == requestCaptcha ||
//		 !resultCaptcha.equalsIgnoreCase(requestCaptcha)) {
//		 throw new CaptchaAuthenticationException("Wrong Captcha");
//		 }
		 }
		return super.attemptAuthentication(request, response);
	}

}
