package com.fzy.design.adapter;

import com.fzy.design.service.UserService;
import org.springframework.stereotype.Component;

/**
 * 通过继承 {@link UserService}，复用已有方法，并在此处进行代码扩展，实现第三方账号授权登录功能
 *
 * @author dayang
 */
@Component
public class Login3rdAdapter extends UserService implements Login3rdTarget {

	@Override
	public String loginByGitee(String code, String state) {
		return "";
	}

	@Override
	public String loginByWechat(String... params) {
		return "";
	}

	@Override
	public String loginByQQ(String... params) {
		return "";
	}

}
