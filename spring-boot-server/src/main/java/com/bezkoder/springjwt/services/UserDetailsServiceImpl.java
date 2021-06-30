package com.bezkoder.springjwt.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bezkoder.springjwt.models.CustomUser;
import com.bezkoder.springjwt.repository.CustomUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	CustomUserRepository customUserRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUser user = customUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		Collection<? extends GrantedAuthority> authorities =
				getAuthorities();
		return buildUserForAuthentication(user, authorities);
	}
	
	// Converts CustomUser to spring.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(CustomUser user,
    		Collection<? extends GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(),
            true, true, true, true, authorities);
    }
    
    private Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));  // since only User role and no other roles
	}

}
