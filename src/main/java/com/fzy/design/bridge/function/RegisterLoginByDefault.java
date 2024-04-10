package com.fzy.design.bridge.function;

import com.fzy.design.pojo.UserInfo;
import com.fzy.design.repo.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 当前设计略有瑕疵，默认登录不需要实现第三方登录的方法
 * <p>
 * </p>
 * 同样，第三方登录（Gitee登录）也不需要实现默认的 login 和 register 方法，后续优化
 */
@Component
public class RegisterLoginByDefault extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

	@Resource
	private UserRepository userRepository;

	/**
	 * 重构 login 方法，将之前
	 * {@link com.fzy.design.service.UserService#login(String, String)}的逻辑完全复制过来即可
	 */
	@Override
	public String login(String account, String password) {
		UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
		if (userInfo == null) {
			return "account / password ERROR!";
		}
		return "Login Success";
	}

	/**
	 * 重构 register 方法，将之前
	 * {@link com.fzy.design.service.UserService#register(UserInfo)}的逻辑完全复制过来即可
	 */
	@Override
	public String register(UserInfo userInfo) {
		if (checkUserExists(userInfo.getUserName())) {
			throw new RuntimeException("User already registered.");
		}
		userInfo.setCreateDate(new Date());
		userRepository.save(userInfo);
		return "Register Success!";
	}

	/**
	 * 重构 checkUserExists 方法，将之前
	 * {@link com.fzy.design.service.UserService#checkUserExists(String)}的逻辑完全复制过来即可
	 */
	@Override
	public boolean checkUserExists(String userName) {
		UserInfo user = userRepository.findByUserName(userName);
		return user != null;
	}

}
