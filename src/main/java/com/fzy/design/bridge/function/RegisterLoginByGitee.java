package com.fzy.design.bridge.function;

import com.fzy.design.pojo.UserInfo;
import com.fzy.design.repo.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 当前设计略有瑕疵，默认登录不需要实现第三方登录的方法
 * <p>
 * </p>
 * 同样，第三方登录（Gitee登录）也不需要实现默认的 login 和 register 方法，后续优化
 */
@Component
public class RegisterLoginByGitee implements RegisterLoginFuncInterface {

	@Resource
	private UserRepository userRepository;

	@Override
	public String login(String account, String password) {
		return "";
	}

	@Override
	public String register(UserInfo userInfo) {
		return "";
	}

	@Override
	public boolean checkUserExists(String userName) {
		return false;
	}

	/**
	 * 第三方账户登录接口
	 * @param request 没有区分是哪个第三方平台，需要一个范围更大的参数类型来接收
	 * @return
	 */
	@Override
	public String login3rd(HttpServletRequest request) {
		return "";
	}

}
