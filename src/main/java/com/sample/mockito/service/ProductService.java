package com.sample.mockito.service;

import org.springframework.stereotype.Component;

import com.sample.mockito.dao.ProductDao;
import com.sample.mockito.resource.Product;

@Component
public class ProductService {

	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public boolean buy(Product product, int orderCount) throws InsufficientProductsException {
		boolean transStatus = false;
		int checkAvailableCount = productDao.getAvailableCount(product);
		if (checkAvailableCount >= orderCount) {
			transStatus = productDao.orderProduct(product, orderCount);
		} else {
			throw new InsufficientProductsException();
		}

		return transStatus;
	}
}
