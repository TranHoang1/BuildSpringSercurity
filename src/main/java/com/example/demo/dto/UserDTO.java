package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

	private Long id;
	private String userName;
	private String passWord;
	private List<RoleDTO> roles = new ArrayList<>();

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public UserDTO(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
