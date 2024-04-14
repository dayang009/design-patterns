package com.fzy.design.items.composite;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductComposite extends AbstractProductItem implements Serializable {

	private static final long serialVersionUID = 8181023817924853990L;

	/**
	 * 主键
	 */
	private int id;

	/**
	 * 父 ID
	 */
	private int pid;

	private String name;

	/**
	 * 选用 AbstractProductItem 泛型主要是为了以后扩展方便
	 */
	private List<AbstractProductItem> child = new ArrayList<>();

	@Override
	public void addProductItem(AbstractProductItem item) {
		this.child.add(item);
	}

	@Override
	public void delProductChild(AbstractProductItem item) {
		ProductComposite removeItem = (ProductComposite) item;
		Iterator iterator = child.iterator();
		while (iterator.hasNext()) {
			ProductComposite composite = (ProductComposite) iterator.next();
			// 移除 ID 相同的商品类目
			if (composite.getId() == removeItem.getId()) {
				iterator.remove();
				break;
			}
		}
	}

}
