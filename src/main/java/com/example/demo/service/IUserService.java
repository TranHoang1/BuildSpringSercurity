package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;

@Service
public interface IUserService {

	UserDTO Save(UserDTO dto);

	List<UserDTO> findAll();
}
