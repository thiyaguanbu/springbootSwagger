package com.springbootswagger.example.exception;

public class ProductNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(Integer id){
		super("Requested product with Id:"+id+" is not found");
	}
}
