package com.bezkoder.springjwt.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.CustomUser;
import com.bezkoder.springjwt.repository.CustomUserRepository;

@Service
public class CustomUserServiceImpl implements CustomUserService{
	@Autowired CustomUserRepository customUserRepository;

	@Override
	public Boolean existsByUsername(String username) {
		
		return customUserRepository.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		
		return customUserRepository.existsByEmail(email);
	}

	@Override
	public Optional<CustomUser> findByUsername(String username) {
		return customUserRepository.findByUsername(username);
	}

	@Override
	public void save(CustomUser user) {
		customUserRepository.save(user);
	}

}
