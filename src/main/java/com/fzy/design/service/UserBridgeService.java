package com.fzy.design.service;

import com.fzy.design.bridge.abst.AbstractRegisterLoginComponent;
import com.fzy.design.bridge.abst.factory.RegisterLoginComponentFactory;
import com.fzy.design.pojo.UserInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
		AbstractRegisterLoginComponent registerLoginComponent = RegisterLoginComponentFactory.getComponent("Default");

		return registerLoginComponent.login(account, password);
	}

	/**
	 * 用户名，密码注册
	 * @param userInfo 用户信息
	 */
	public String register(UserInfo userInfo) {
		AbstractRegisterLoginComponent registerLoginComponent = RegisterLoginComponentFactory.getComponent("Default");
		return registerLoginComponent.register(userInfo);
	}

	/**
	 * 第三方平台注册
	 */
	public String login3rd(HttpServletRequest request, String type) {
		AbstractRegisterLoginComponent component = RegisterLoginComponentFactory.getComponent(type);
		return component.login3rd(request);
	}

}
