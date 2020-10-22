package com.sample.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author manasa
 * This is a way to enable web security
 */
@EnableWebSecurity 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	//This is for authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			//set configuration on the auth object
		auth.inMemoryAuthentication()
			.withUser("foo")
			.password(getPasswordEncoder().encode("foo"))
			.roles("USER")
			.and()
			.withUser("blah")
			.password(getPasswordEncoder().encode("blah"))
			.roles("ADMIN");
	}
	
	//This is for authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("USER","ADMIN")
			.antMatchers("/").permitAll()
			.and().formLogin();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
