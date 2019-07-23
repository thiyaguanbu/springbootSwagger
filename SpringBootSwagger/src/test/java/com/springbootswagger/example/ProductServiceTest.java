package com.springbootswagger.example;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springbootswagger.example.model.Product;
import com.springbootswagger.example.repository.ProductJpaRepository;
import com.springbootswagger.example.services.ProductServiceImpl;

/**
 * 
 * @author thiyagu.anbu
 *
 *Test the Service Layer
 *junit
 *mockito
 */


@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {
	@Mock
	private ProductJpaRepository productJpaRepository;
	
	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testgetAllProduct() {
		List<Product> list = new ArrayList<Product>();
		list.add(new Product(59, 2, "v9", "moto9 android phone", "www.flipkart.com/motoandroidphone9", new BigDecimal("11005.00")));
		list.add(new Product(60, 2, "v9", "moto9 android phone", "www.flipkart.com/motoandroidphone9", new BigDecimal("11005.00")));
		list.add(new Product(70, 2, "v9", "moto9 android phone", "www.flipkart.com/motoandroidphone9", new BigDecimal("11005.00")));
		when(productJpaRepository.findAll()).thenReturn(list);
		List<Product> productList = productServiceImpl.listAllProducts();
		assertEquals(3, productList.size());
	}
	
	@Test
	public void testgetProductById() {
		Product product = new Product(59, 2, "v9", "moto9 android phone", "www.flipkart.com/motoandroidphone9", new BigDecimal("11005.00"));
		when(productJpaRepository.getOne(59)).thenReturn(product);
		System.out.println(productJpaRepository.getOne(59).getProductId());
		Product resultProduct = productServiceImpl.getProductId(59);
		assertEquals("www.flipkart.com/motoandroidphone9", resultProduct.getImageUrl());
		assertEquals("v9", resultProduct.getProductId());
		
	}
	
	public void testsaveProduct() {
		
	}
	
	
	
	
}
