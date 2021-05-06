package com.training.boot.config;



import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

// Custom Security, Create user, Authentication Manager, UserDetailService

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// permissions given to API URLs
		// /contact /notice /patients/{id}
		// http.authorizeRequests()
		// .anyRequest().authenticated();

		http.authorizeRequests().antMatchers("/notice").authenticated().antMatchers("/contact").permitAll().and()
				.formLogin().and().httpBasic(); // API - POSTMAN or Angular
	}

	// PasswordEncoder interface -> few implementation - NoOpPasswordEncoder,
	// BcryptPasswordEncoder
	// Customize the user credentials in the app
	// Every User object in security should come from USerDetails
	//@Override
	//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * auth.inMemoryAuthentication().withUser("admin").password("12345").authorities
		 * ("admin") .and().withUser("user").password("12345").authorities("user")
		 * .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
		 */
// setup the userDetailManager - in-memory
	/*	InMemoryUserDetailsManager userDetailService = new InMemoryUserDetailsManager();
		// userDetailService.createUser(new User());
		UserDetails user1 = User.withUsername("admin").password("12345").authorities("admin").build();
		UserDetails user2 = User.withUsername("user").password("12345").authorities("user").build();
		userDetailService.createUser(user1);
		userDetailService.createUser(user2);

		auth.userDetailsService(userDetailService);
	}
	*/
	
	
	@Bean
	public UserDetailsService userDetailService(DataSource dataSource)
	{
	   return new JdbcUserDetailsManager(dataSource);
	}
	
	
	// passwordencoder - hashing algorithm 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
