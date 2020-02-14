package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RoleDTO;
import com.example.demo.service.IRoleService;

@RestController
public class RoleAPI {

	@Autowired
	private IRoleService iRoleService;

	@PostMapping("/api/role")
	public RoleDTO SaveRole(@RequestBody RoleDTO dto) {
		return iRoleService.Save(dto);
	}
}
