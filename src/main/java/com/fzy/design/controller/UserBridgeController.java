package com.fzy.design.controller;

import com.fzy.design.service.UserBridgeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/bridge")
public class UserBridgeController {

	@Resource
	private UserBridgeService userBridgeService;

}
