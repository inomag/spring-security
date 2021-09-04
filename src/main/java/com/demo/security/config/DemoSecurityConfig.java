package com.demo.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	// ADD REFERNCE TO SECURITY DATASOURCE
	@Autowired
	private DataSource securityDataSource;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// ADD USERS
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		
//		auth.inMemoryAuthentication()
//		.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
//		.withUser(users.username("mary").password("test123").roles("MANAGER","EMPLOYEE"))
//		.withUser(users.username("susan").password("test123").roles("ADMIN","EMPLOYEE"));
		
		// JDBC AUTHENTICATION
		
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/auth")
				.permitAll()
			.and()
			.logout()
			.permitAll()
			.and()
			.exceptionHandling()
			.accessDeniedPage("/denied");
	}

	
}
