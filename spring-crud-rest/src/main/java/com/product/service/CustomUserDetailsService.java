package com.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.product.model.User;
import com.product.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{
    
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=repository.getUserByUsername (username);
		if(user==null) {	
		throw new UsernameNotFoundException("User Not Exits with name:"+username);
		}
		
		return new CustomUserDetails(user);
	}

}
