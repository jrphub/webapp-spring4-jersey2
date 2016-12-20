package com.simple.mockito;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sample.mockito.dao.ProductDao;
import com.sample.mockito.resource.Product;
import com.sample.mockito.service.InsufficientProductsException;
import com.sample.mockito.service.ProductService;

public class MockCreationAnnotationTest {
	@Mock
	private ProductDao productDao;
	@Mock
	private Product product;

	private ProductService productService;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		// or specify MockitoJUnitRunner as the JUnit test runner as
		// @RunWith(MockitoJUnitRunner.class)
		productService = new ProductService();
		productService.setProductDao(productDao);
	}

	@Test
	public void testMockCreation() {
		assertNotNull(product);
		assertNotNull(productDao);
	}

	@Test
	public void testBuy() throws InsufficientProductsException {
		System.out.println("====testBuy() : START====");
		int availableQuantity = 20;
		int purchaseQuantity = 15;

		System.out
				.println("Stubbing getAvailableProductCount(product) to return "
						+ availableQuantity);
		when(productDao.getAvailableCount(product)).thenReturn(
				availableQuantity);
		System.out.println("Calling ProductService.buy(product,"
				+ purchaseQuantity + ")");

		productService.buy(product, purchaseQuantity);

		System.out.println("Verifying ProductDao(product, " + purchaseQuantity
				+ ") is called");
		verify(productDao).orderProduct(product, purchaseQuantity);
		System.out.println("====testBuy() : END====");
	}

	@Test(expected = InsufficientProductsException.class)
	public void purchaseWithInsufficientAvailableQuantity()
			throws InsufficientProductsException {
		System.out
				.println("====purchaseWithInsufficientAvailableQuantity : START====");
		int availableQuantity = 3;
		int purchaseQuantity = 5;
		System.out.println("Stubbing getAvailableProducts(product) to return "
				+ availableQuantity);
		when(productDao.getAvailableCount(product)).thenReturn(
				availableQuantity);
		try {
			System.out.println("productService.buy(product" + purchaseQuantity
					+ ") should throw InsufficientProductsException");
			productService.buy(product, purchaseQuantity);
		} catch (InsufficientProductsException e) {
			System.out.println("InsufficientProductsException has been thrown");
			verify(productDao, times(0))
					.orderProduct(product, purchaseQuantity);
			System.out.println("Verified orderProduct(product, "
					+ purchaseQuantity + ") is not called");
			throw e;
		} finally {
			System.out
			.println("====purchaseWithInsufficientAvailableQuantity : END====");
		}

	}

}