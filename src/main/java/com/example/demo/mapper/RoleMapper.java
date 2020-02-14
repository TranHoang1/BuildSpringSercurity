package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.dto.RoleDTO;
import com.example.demo.entity.RoleEntity;

@Component
public class RoleMapper {
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public RoleEntity toEntity(RoleDTO dto) {
		return modelMapper().map(dto, RoleEntity.class);
	}

	public RoleDTO toDto(RoleEntity entity) {
		return modelMapper().map(entity, RoleDTO.class);
	}

}
