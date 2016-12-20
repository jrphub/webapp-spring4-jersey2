package com.simple.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import com.sample.mockito.dao.ProductDao;
import com.sample.mockito.resource.Product;

public class MockCreationTest {
    private ProductDao productDao;
    private Product product;
    @Before
    public void setupMock() {
        product = mock(Product.class);
        productDao = mock(ProductDao.class);
    }
    @Test
    public void testMockCreation(){
        assertNotNull(product);
        assertNotNull(productDao);
    }
}