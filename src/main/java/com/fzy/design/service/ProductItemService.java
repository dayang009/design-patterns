package com.fzy.design.service;

import com.fzy.design.items.composite.AbstractProductItem;
import com.fzy.design.items.composite.ProductComposite;
import com.fzy.design.pojo.ProductItem;
import com.fzy.design.repo.ProductItemRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductItemService {

	@Resource
	private RedisTemplate redisTemplate;

	// 引入上平类目查询持久化组件
	@Resource
	private ProductItemRepository productItemRepository;

	// 获取商品类目信息
	public ProductComposite fetchAllItems() {
		// 先查询 Redis 缓存，如果不为 null，则直接返回
		Object cacheItems = redisTemplate.opsForValue().get("items");
		if (cacheItems != null) {
			return (ProductComposite) cacheItems;
		}
		// 否则从数据库里查询
		List<ProductItem> fetchDbItems = productItemRepository.findAll();

		// 将数据库查询出来的数据组合为树形结构
		ProductComposite items = generateProductTree(fetchDbItems);
		if (items == null) {
			throw new UnsupportedOperationException("Product items should not be empty in DB!");
		}
		// 并且将商品类目信息设置到 Redis 中，以便下次可以直接从缓存获取数据
		redisTemplate.opsForValue().set("items", items);
		return items;
	}

	private ProductComposite generateProductTree(List<ProductItem> fetchDbItems) {
		List<ProductComposite> composites = new ArrayList<>(fetchDbItems.size());
		fetchDbItems.forEach(dbItem -> composites
			.add(ProductComposite.builder().id(dbItem.getId()).name(dbItem.getName()).pid(dbItem.getPid()).build()));
		Map<Integer, List<ProductComposite>> groupingList = composites.stream()
			.collect(Collectors.groupingBy(ProductComposite::getPid));
		composites.forEach(item -> {
			List<ProductComposite> list = groupingList.get(item.getId());
			item.setChild(list == null ? new ArrayList<>()
					: list.stream().map(x -> (AbstractProductItem) x).collect(Collectors.toList()));
		});
		ProductComposite composite = composites.isEmpty() ? null : composites.get(0);
		return composite;
	}

}
