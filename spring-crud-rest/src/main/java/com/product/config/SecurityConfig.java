package com.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.product.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(auth);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/getAllEmployees").hasAnyAuthority("admin", "creator", "editor")
				.antMatchers("/saveEmp").hasAnyAuthority("admin", "creator").antMatchers("/update/**")
				.hasAnyAuthority("admin", "editor").antMatchers("/delete/**").hasAuthority("admin").anyRequest()
				.authenticated().and().formLogin().permitAll().and().logout().permitAll();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	 
	
	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception{
	 * 
	 * http.authorizeRequests() .antMatchers("/api/rest/").permitAll()
	 * .antMatchers("/getAllEmployees").hasAnyAuthority("admin","editor", "creator")
	 * .antMatchers("/saveEmp").hasAnyAuthority("admin", "creator")
	 * .antMatchers("/update/**").hasAnyAuthority("admin", "editor")
	 * .antMatchers("/delete/**").hasAuthority("admin")
	 * .anyRequest().authenticated(); return http.build();
	 * 
	 * }
	 */
}
