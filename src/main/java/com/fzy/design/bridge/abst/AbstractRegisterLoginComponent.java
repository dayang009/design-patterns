package com.fzy.design.bridge.abst;

import com.fzy.design.bridge.function.RegisterLoginFuncInterface;
import com.fzy.design.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建一个抽象类，为 Client 调用提供 login() 的抽象方法、register() 的抽象方法
 * <p>
 * </p>
 * checkUserExists() 的抽象方法和第三方登录的抽象方法
 */
public abstract class AbstractRegisterLoginComponent {

	// 面向接口编程，引入该接口属性。此处为“桥”之所在
	protected RegisterLoginFuncInterface funcInterface;

	// 通过有参构造函数，初始化 RegisterLoginFuncInterface 属性
	public AbstractRegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
		// 校验参数类型且不为 null
		this.validate(funcInterface);
		this.funcInterface = funcInterface;
	}

	/**
	 * 参数校验，接口类型不为 null。final方法，不允许子类重写
	 * @param funcInterface
	 */
	protected final void validate(RegisterLoginFuncInterface funcInterface) {
		if (!(funcInterface instanceof RegisterLoginFuncInterface)) {
			throw new UnsupportedOperationException("Unknown register/login function type!");
		}
	}

	public abstract String login(String username, String password);

	public abstract String register(UserInfo userInfo);

	public abstract boolean checkUserExists(String userName);

	public abstract String login3rd(HttpServletRequest request);

}
