package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;

@Component
public class UserMapper {
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public UserEntity toEntity(UserDTO dto) {
		return modelMapper().map(dto, UserEntity.class);
	}

	public UserDTO toDto(UserEntity entity) {
		return modelMapper().map(entity, UserDTO.class);
	}

}
