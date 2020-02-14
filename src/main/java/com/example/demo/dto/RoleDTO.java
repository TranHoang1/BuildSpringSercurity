package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.UserEntity;

public class RoleDTO {

	private Long id;
	private String role;
	private List<UserEntity> entities = new ArrayList<>();

	public List<UserEntity> getEntities() {
		return entities;
	}

	public void setEntities(List<UserEntity> entities) {
		this.entities = entities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
