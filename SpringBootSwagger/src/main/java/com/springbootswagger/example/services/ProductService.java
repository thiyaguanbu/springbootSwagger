package com.springbootswagger.example.services;

import java.util.List;


import com.springbootswagger.example.model.Product;


public interface ProductService {
	
	List<Product> listAllProducts();
	Product getProductById(Integer id);
	void saveProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(Integer id);

}
