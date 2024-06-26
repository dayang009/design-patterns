package com.fzy.design.bridge.function;

import com.fzy.design.bridge.abst.factory.RegisterLoginComponentFactory;
import com.fzy.design.pojo.UserInfo;
import com.fzy.design.utils.HttpClientUtils;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class RegisterLoginByGitee extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

	@Value("${gitee.state}")
	private String giteeState;

	@Value("${gitee.token.url}")
	private String giteeTokenUrl;

	@Value("${gitee.user.url}")
	private String giteeUserUrl;

	@Value("${gitee.user.prefix}")
	private String giteeUserPrefix;

	/**
	 * 第三方账户登录接口，代码实现功能直接复制过来
	 * @param request 没有区分是哪个第三方平台，需要一个范围更大的参数类型来接收
	 * @return
	 */
	@Override
	public String login3rd(HttpServletRequest request) {

		String code = request.getParameter("code");
		String state = request.getParameter("state");

		// 进行state判断，state的值是前端与后端商定好的，前端将State传给Gitee平台，Gitee平台回传state给回调接口
		if (!giteeState.equals(state)) {
			throw new UnsupportedOperationException("Invalid state!");
		}

		// 请求Gitee平台获取token，并携带code
		String tokenUrl = giteeTokenUrl.concat(code);
		JsonObject tokenResponse = HttpClientUtils.execute(tokenUrl, HttpMethod.POST);
		String token = tokenResponse.get("access_token").getAsString();
		System.out.println(token);
		// 请求用户信息，并携带 token
		String userUrl = giteeUserUrl.concat(token);
		JsonObject userInfoResponse = HttpClientUtils.execute(userUrl, HttpMethod.GET);

		// 获取用户信息，userName添加前缀 GITEE@, 密码保持与userName一致。讨论过程请参见2.3小节
		String userName = giteeUserPrefix.concat(userInfoResponse.get("name").getAsString());
		String password = userName;

		// “自动注册” 和登录功能，此处体现了方法的 “复用”
		return autoRegister3rdAndLogin(userName, password);
	}

	private String autoRegister3rdAndLogin(String userName, String password) {
		// 如果第三方账号已经登录过，则直接登录
		if (this.checkUserExists(userName)) {
			return this.login(userName, password);
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);
		userInfo.setUserPassword(password);
		userInfo.setCreateDate(new Date());

		// 如果第三方账号是第一次登录，先进行“自动注册”
		this.register(userInfo);
		// 自动注册完成后，进行登录
		return this.login(userName, password);
	}

	@PostConstruct
	private void initFuncMap() {
		RegisterLoginComponentFactory.funcMap.put("Gitee", this);
	}

}
