package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.IUserService;

@RestController
public class UserAPI {

	@Autowired
	private IUserService userService;

	@PostMapping("/api/user")
	public UserDTO saveDTOWithBCryCode(@RequestBody UserDTO dto) {
		return userService.Save(dto);
	}

	@GetMapping("api/all")
	public List<UserDTO> findAllUser() {
		return userService.findAll();
	}

}
