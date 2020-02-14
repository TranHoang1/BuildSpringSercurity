package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRepository userRepository;

	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public UserDTO Save(UserDTO dto) {
		UserEntity entity = userMapper.toEntity(dto);
		entity.setPassWord(encoder().encode(dto.getPassWord()));
		userRepository.save(entity);
		return userMapper.toDto(entity);
	}

	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> dtos = new ArrayList<>();
		for (UserEntity entity : userRepository.findAll()) {
			dtos.add(userMapper.toDto(entity));
		}
		return dtos;
	}

}
