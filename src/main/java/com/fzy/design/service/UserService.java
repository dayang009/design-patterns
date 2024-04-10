package com.fzy.design.service;

import com.fzy.design.pojo.UserInfo;
import com.fzy.design.repo.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserService {

	@Resource
	private UserRepository userRepository;

	public String login(String account, String password) {
		UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
		if (userInfo == null) {
			return "account / password ERROR!";
		}
		return "Login Success";
	}

	public String register(UserInfo userInfo) {
		if (checkUserExists(userInfo.getUserName())) {
			throw new RuntimeException("User already registered.");
		}
		userInfo.setCreateDate(new Date());
		userRepository.save(userInfo);
		return "Register Success!";
	}

	public boolean checkUserExists(String userName) {
		UserInfo user = userRepository.findByUserName(userName);
		return user != null;
	}

}
