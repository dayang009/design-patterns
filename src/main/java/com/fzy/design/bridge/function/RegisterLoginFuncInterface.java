package com.fzy.design.bridge.function;

import com.fzy.design.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册登录函数接口
 */
public interface RegisterLoginFuncInterface {

	String login(String account, String password);

	String register(UserInfo userInfo);

	boolean checkUserExists(String userName);

	/**
	 * 第三方账户登录接口
	 * @param request 没有区分是哪个第三方平台，需要一个范围更大的参数类型来接收
	 * @return
	 */
	String login3rd(HttpServletRequest request);

}
