package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RoleDTO;
import com.example.demo.entity.RoleEntity;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleDTO Save(RoleDTO dto) {
		RoleEntity entity = roleMapper.toEntity(dto);

		return roleMapper.toDto(roleRepository.save(entity));
	}

}
