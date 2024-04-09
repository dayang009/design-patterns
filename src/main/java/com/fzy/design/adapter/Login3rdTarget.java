package com.fzy.design.adapter;

public interface Login3rdTarget {

	String loginByGitee(String code, String state);

	String loginByWechat(String... params);

	String loginByQQ(String... params);

}
