package com.jy.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jy.service.login.CaptchaAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService UserDetailsService;

	@Autowired
	SessionRegistry sessionRegistry;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.UserDetailsService);
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(this.UserDetailsService).passwordEncoder(new StandardPasswordEncoder("syzn"));
//	}
	

	@Override
	public void configure(WebSecurity web) throws Exception {
		DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
		handler.setPermissionEvaluator(new MyPermissionEvaluator());
		web.expressionHandler(handler);
		web.ignoring().antMatchers("/static/**", "/favicon.ico", "/assets/**", "/audio/**", "/css/**", "/data/**", "/images/**", "/js/**",
				"fonts/**","/mapfiles/**","/websocket","/device/mapFindDevice","/REST-API/*");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").permitAll().and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/login","/signup", "/captcha").permitAll().and().authorizeRequests()
				.antMatchers(HttpMethod.POST,"/system/addOperator","/captcha").permitAll().and().authorizeRequests().anyRequest()
				.authenticated().and().logout().deleteCookies("jy_innerUrl").permitAll().and()
				.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(concurrentSessionFilter(), ConcurrentSessionFilter.class);
		http.csrf().disable().headers().frameOptions().sameOrigin();

	}

	@Bean
	public ConcurrentSessionFilter concurrentSessionFilter() {
		return new ConcurrentSessionFilter(sessionRegistry);

	}

	@Bean
	public CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy() {
		List<SessionAuthenticationStrategy> SessionAuthenticationStrategys = new ArrayList<SessionAuthenticationStrategy>();
		ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(
				sessionRegistry);
		concurrentSessionControlAuthenticationStrategy.setMaximumSessions(1);
		concurrentSessionControlAuthenticationStrategy.setExceptionIfMaximumExceeded(true);
		SessionFixationProtectionStrategy SessionFixationProtectionStrategy = new SessionFixationProtectionStrategy();
		RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy = new RegisterSessionAuthenticationStrategy(
				sessionRegistry);
		SessionAuthenticationStrategys.add(concurrentSessionControlAuthenticationStrategy);
		SessionAuthenticationStrategys.add(SessionFixationProtectionStrategy);
		SessionAuthenticationStrategys.add(registerSessionAuthenticationStrategy);
		return new CompositeSessionAuthenticationStrategy(SessionAuthenticationStrategys);
	}

	// @Bean
	// public ServletListenerRegistrationBean<HttpSessionEventPublisher>
	// httpSessionEventPublisher() {
	// return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new
	// HttpSessionEventPublisher());
	// }

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public CaptchaAuthenticationFilter authenticationFilter() throws Exception {
		CaptchaAuthenticationFilter captchaAuthenticationFilter = new CaptchaAuthenticationFilter();
		captchaAuthenticationFilter
				.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
		captchaAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
		captchaAuthenticationFilter
				.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/redirect"));
		captchaAuthenticationFilter.setAuthenticationFailureHandler(exceptionMappingAuthenticationFailureHandler());
		captchaAuthenticationFilter.setUsernameParameter("username");
		captchaAuthenticationFilter.setPasswordParameter("password");
		
		captchaAuthenticationFilter.setSessionAuthenticationStrategy(compositeSessionAuthenticationStrategy());
		return captchaAuthenticationFilter;
	}

	@Bean
	public ExceptionMappingAuthenticationFailureHandler exceptionMappingAuthenticationFailureHandler() {
		ExceptionMappingAuthenticationFailureHandler ex = new ExceptionMappingAuthenticationFailureHandler();
		Map<String, String> mappings = new HashMap<String, String>();
		mappings.put("com.sjgk.tunnel.exception.CaptchaAuthenticationException", "/login?error=0");
		mappings.put("org.springframework.security.authentication.InternalAuthenticationServiceException",
				"/login?error=1");
		mappings.put("org.springframework.security.authentication.BadCredentialsException", "/login?error=2");
		ex.setExceptionMappings(mappings);
		return ex;
	}


}
