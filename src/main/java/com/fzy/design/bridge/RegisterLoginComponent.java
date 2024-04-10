package com.fzy.design.bridge;

import com.fzy.design.bridge.abst.AbstractRegisterLoginComponent;
import com.fzy.design.bridge.function.RegisterLoginFuncInterface;
import com.fzy.design.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 抽象角色子类，桥接模式的关键，能够让当前子类调用到 Implementor 结构体系中的方法
 */
public class RegisterLoginComponent extends AbstractRegisterLoginComponent {

	/**
	 * 通过构造函数，传入“桥梁” RegisterLoginFuncInterface 的具体类型
	 * @param funcInterface 桥梁接口
	 */
	public RegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
		super(funcInterface);
	}

	@Override
	public String login(String username, String password) {
		// 直接通过桥梁，调用 Implementor 的方法即可，把具体的实现交给右路的实现类
		return funcInterface.login(username, password);
	}

	@Override
	public String register(UserInfo userInfo) {
		return funcInterface.register(userInfo);
	}

	@Override
	public boolean checkUserExists(String userName) {
		return funcInterface.checkUserExists(userName);
	}

	@Override
	public String login3rd(HttpServletRequest request) {
		return funcInterface.login3rd(request);
	}

}
