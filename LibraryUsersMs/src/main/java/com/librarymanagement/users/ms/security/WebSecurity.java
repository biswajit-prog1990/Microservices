package com.librarymanagement.users.ms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.librarymanagement.users.ms.service.IUserService;
import com.librarymanagement.users.ms.shared.Util;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private Environment environment;
	private IUserService iUserService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private Util util;

	@Autowired
	public WebSecurity(Environment environment, IUserService iUserService,
			BCryptPasswordEncoder bCryptPasswordEncoder, Util util) {
		this.environment = environment;
		this.iUserService = iUserService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.util = util;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/**").hasIpAddress(environment.getProperty("gateway.api.ip")) // Remote Server Gateway IP Address
				.antMatchers("/**").hasIpAddress(util.getSystemIpAddress())          // Localhost IP Address
				.and()
				.addFilter(getAuthenticationFilter()); 
		http.headers().frameOptions().disable();
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(iUserService, environment,
				authenticationManager());
		// authenticationFilter.setAuthenticationManager(authenticationManager());
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		return authenticationFilter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(iUserService).passwordEncoder(bCryptPasswordEncoder);
	}
}
