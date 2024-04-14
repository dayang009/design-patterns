package com.fzy.design.controller;

import com.fzy.design.items.composite.ProductComposite;
import com.fzy.design.service.ProductItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品类目查询接口
 */
@RestController
@RequestMapping("/product")
public class ProductItemController {

	@Resource
	private ProductItemService productItemService;

	/**
	 * 前端获取商品类目新信息
	 */
	@PostMapping("/fetchAllItems")
	public ProductComposite fetchAllItems() {
		return productItemService.fetchAllItems();
	}

}
