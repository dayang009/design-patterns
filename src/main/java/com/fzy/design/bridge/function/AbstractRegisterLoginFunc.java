package com.fzy.design.bridge.function;

import com.fzy.design.pojo.UserInfo;
import com.fzy.design.repo.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 这个抽象层是解决瑕疵代码的关键
 */
public abstract class AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

	/**
	 * 新增 commonLogin 方法，新增 UserRepository 参数，代码逻辑与子类中的 login 方法完全一致 protectes修饰方法，仅供子类使用
	 */
	protected String commonLogin(String account, String password, UserRepository userRepository) {
		UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
		if (userInfo == null) {
			return "account / password ERROR!";
		}
		return "Login Success";
	}

	/**
	 * 新增 commonRegister 方法，新增 UserRepository 参数，代码逻辑与子类中的 register 方法完全一致
	 * protectes修饰方法，仅供子类使用
	 */
	protected String commonRegister(UserInfo userInfo, UserRepository userRepository) {
		if (commonCheckUserExists(userInfo.getUserName(), userRepository)) {
			throw new RuntimeException("User already registered.");
		}
		userInfo.setCreateDate(new Date());
		userRepository.save(userInfo);
		return "Register Success!";
	}

	/**
	 * 新增 commonCheckUserExists 方法，新增 UserRepository 参数，代码逻辑与子类中的 checkUserExists 方法完全一致
	 * protectes修饰方法，仅供子类使用.
	 */
	protected boolean commonCheckUserExists(String userName, UserRepository userRepository) {
		UserInfo user = userRepository.findByUserName(userName);
		return user != null;
	}

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
