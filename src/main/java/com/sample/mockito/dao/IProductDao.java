package com.sample.mockito.dao;

import com.sample.mockito.resource.Product;

public interface IProductDao {
	
	int getAvailableCount(Product product);

	boolean orderProduct(Product product, int orderQuantity);
}
