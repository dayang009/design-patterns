package com.fzy.design.service;

import com.fzy.design.bridge.RegisterLoginComponent;
import com.fzy.design.bridge.abst.AbstractRegisterLoginComponent;
import com.fzy.design.bridge.function.RegisterLoginByDefault;
import com.fzy.design.pojo.UserInfo;
import org.springframework.stereotype.Service;

/**
 * 通过 new 关键字创建，传入桥梁和实现类，功能事项。
 * <p>
 * </p>
 * 但存在致命问题，例如，每个进行登录的用户，都会 new 两个对象，影响性能
 */
@Service
public class UserBridgeService {

	public String login(String account, String password) {

		// 用左路的具体实现子类创建调用入口
		AbstractRegisterLoginComponent registerLoginComponent = new RegisterLoginComponent(
				new RegisterLoginByDefault());

		return registerLoginComponent.login(account, password);
	}

	public String register(UserInfo userInfo) {
		AbstractRegisterLoginComponent registerLoginComponent = new RegisterLoginComponent(
				new RegisterLoginByDefault());
		return registerLoginComponent.register(userInfo);
	}

	public boolean checkUserExists(String userName) {
		AbstractRegisterLoginComponent registerLoginComponent = new RegisterLoginComponent(
				new RegisterLoginByDefault());
		return registerLoginComponent.checkUserExists(userName);
	}

}
