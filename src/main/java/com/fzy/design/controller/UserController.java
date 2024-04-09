package com.fzy.design.controller;

import com.fzy.design.pojo.UserInfo;
import com.fzy.design.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public String login(String account, String password) {
		return userService.login(account, password);
	}

	@PostMapping("/register")
	public String register(@RequestBody UserInfo userInfo) {
		return userService.register(userInfo);
	}

}
