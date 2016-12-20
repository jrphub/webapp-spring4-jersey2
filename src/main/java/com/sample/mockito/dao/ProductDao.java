package com.sample.mockito.dao;

import com.sample.mockito.resource.Product;

public class ProductDao implements IProductDao {

	@Override
	public int getAvailableCount(Product product) {
		// Here there can be a call to database or any external system
		// But for simplicity, lets assume, from a database call we got the
		// availabilty count of the product
		System.out
				.println("A database call to check the availabilty count of the product");
		return 10;
	}

	@Override
	public boolean orderProduct(Product product, int orderQuantity) {
		// Here also, there will be database call to save the order
		// but for simplicty, lets assume the order is placed
		// and put a print statement which says this method is called
		System.out.println("A Database call to save an order");
		return true;
	}

}
