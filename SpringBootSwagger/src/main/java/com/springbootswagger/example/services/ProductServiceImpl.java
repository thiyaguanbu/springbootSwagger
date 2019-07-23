package com.springbootswagger.example.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootswagger.example.exception.ProductNotFoundException;
import com.springbootswagger.example.model.Product;
import com.springbootswagger.example.repository.ProductJpaRepository;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductJpaRepository productJpaRepo;

	
	@Override
	public List<Product> listAllProducts() {
		logger.debug("ProductService::listAllProducts is called");
		List<Product> productList = productJpaRepo.findAll();
		return productList;
	}

	@Override
	public Product getProductById(Integer id) {
		logger.debug("ProductService::getProductById is called");
		Product product = productJpaRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		return product;
	}
	
	public Product getProductId(Integer id) {
		logger.debug("ProductService::getProductById is called");
		Product product = productJpaRepo.getOne(id);
		return product;
	}
	

	@Override
	public void saveProduct(Product product) {
		logger.debug("ProductService::saveProduct is called");
		productJpaRepo.save(product);
		
	}

	@Override
	public void updateProduct(Product product) {
		logger.debug("ProductService::updateProduct is called");
		Product productToUpdate = getProductById(product.getId());
		productToUpdate.setDescription(product.getDescription());
		productToUpdate.setImageUrl(product.getImageUrl());
		productToUpdate.setPrice(product.getPrice());
		productToUpdate.setVersion(product.getVersion());
		saveProduct(productToUpdate);
	}

	@Override
	public void deleteProduct(Integer id) {
		logger.debug("ProductService::deleteProduct is called");
		productJpaRepo.deleteById(id);
		
	}

}
