package com.hsbc.mann.service.serviceImpl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hsbc.mann.entity.User;
import com.hsbc.mann.repository.UserRepository;
import com.hsbc.mann.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public Integer registerUser(User user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		return repository.save(user).getUserId();
	}
	
	@Override
	public User findByUserEmail(String email) {
		return repository.findByUserEmail(email).get();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User usr = findByUserEmail(email);
		return new org.springframework.security.core.userdetails.User(usr.getUserName(), usr.getPassword()
				,usr.getRoles().stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toSet())
				);
	}

	

}
