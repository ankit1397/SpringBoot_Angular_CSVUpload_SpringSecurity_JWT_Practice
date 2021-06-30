package com.bezkoder.springjwt.services;

import java.util.Optional;

import com.bezkoder.springjwt.models.CustomUser;

public interface CustomUserService {
	public Optional<CustomUser> findByUsername(String username);
	public Boolean existsByUsername(String username);
	public Boolean existsByEmail(String email);
	public void save(CustomUser user);
}
