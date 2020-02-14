package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity entity = userRepository.findOneByUserName(username);
		if (entity == null) {
			throw new UsernameNotFoundException("User Name nost found !! ");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<RoleEntity> roles = entity.getRoles();
		for (RoleEntity roleEntity : roles) {
			authorities.add(new SimpleGrantedAuthority(roleEntity.getRole()));
		}

		return new MyUser(entity.getUserName(), entity.getPassWord(), true, true, true, true, authorities);
	}
}
