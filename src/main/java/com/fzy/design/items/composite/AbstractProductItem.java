package com.fzy.design.items.composite;

/**
 * 创建 Component 抽象组件
 */
public abstract class AbstractProductItem {

	// 增加 商品类目
	protected void addProductItem(AbstractProductItem item) {
		throw new UnsupportedOperationException("Not Support child add!");
	}

	// 移除 商品类目
	protected void delProductChild(AbstractProductItem item) {
		throw new UnsupportedOperationException("Not Support child remove!");
	}

}
