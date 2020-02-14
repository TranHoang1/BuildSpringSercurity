package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RoleDTO;

@Service
public interface IRoleService {
	RoleDTO Save(RoleDTO dto);

}
