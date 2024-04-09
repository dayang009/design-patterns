package com.fzy.design.repo;

import com.fzy.design.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {

	/**
	 * 根据用户 userName查询信息
	 * @param userName
	 * @return
	 */
	UserInfo findByUserName(String userName);

	/**
	 * 根据用 户名称 和 密码 查询用户信息
	 * @param account
	 * @param password
	 * @return
	 */
	UserInfo findByUserNameAndUserPassword(String account, String password);

}
