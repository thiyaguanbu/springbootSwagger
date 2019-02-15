package com.springbootswagger.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootswagger.example.model.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {

	

}
