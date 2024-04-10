package com.fzy.design.bridge.function;

import com.fzy.design.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 这个抽象层是解决瑕疵代码的关键
 */
public abstract class AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

	@Override
	public String login(String account, String password) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String register(UserInfo userInfo) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean checkUserExists(String userName) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String login3rd(HttpServletRequest request) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
