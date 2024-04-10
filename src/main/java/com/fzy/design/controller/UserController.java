package com.fzy.design.controller;

import com.fzy.design.adapter.Login3rdAdapter;
import com.fzy.design.pojo.UserInfo;
import com.fzy.design.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

	@Resource
	private UserService userService;

	@Resource
	private Login3rdAdapter login3rdAdapter;

	@PostMapping("/login")
	public String login(String account, String password) {
		return userService.login(account, password);
	}

	@PostMapping("/register")
	public String register(@RequestBody UserInfo userInfo) {
		return userService.register(userInfo);
	}

	/**
	 * Gitee 平台回调接口，并携带 code 和 state 参数
	 */
	@GetMapping("/gitee")
	public String gitee(String code, String state) {
		return login3rdAdapter.loginByGitee(code, state);
	}

}
