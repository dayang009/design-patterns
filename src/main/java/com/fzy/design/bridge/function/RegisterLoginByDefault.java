package com.fzy.design.bridge.function;

import com.fzy.design.bridge.abst.factory.RegisterLoginComponentFactory;
import com.fzy.design.pojo.UserInfo;
import com.fzy.design.repo.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class RegisterLoginByDefault extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

	@Resource
	private UserRepository userRepository;

	@Override
	public String login(String account, String password) {
		return super.commonLogin(account, password, userRepository);
	}

	@Override
	public String register(UserInfo userInfo) {
		return super.commonRegister(userInfo, userRepository);
	}

	@Override
	public boolean checkUserExists(String userName) {
		return super.commonCheckUserExists(userName, userRepository);
	}

	@PostConstruct
	private void initFuncMap() {
		RegisterLoginComponentFactory.funcMap.put("Default", this);
	}

}
