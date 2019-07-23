package com.springbootswagger.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springbootswagger.example.exception.ProductException;
import com.springbootswagger.example.model.Product;
import com.springbootswagger.example.model.Response;
import com.springbootswagger.example.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
@Api(value="Online Store")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	

	@Autowired
	ProductService productService;
	
	@ApiOperation(value="To get List of product Resources", response=List.class,httpMethod="GET",nickname="getAllProduct")
	 @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "List is Reterived Successfully"),
	            @ApiResponse(code = 401, message = "You are not authorized user to view the resource"),
	            @ApiResponse(code = 403, message = "The resource you trying to access is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not available")
	    }
	    )
	@RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
	public List<Product> getAllProduct(){
		List<Product> productList = productService.listAllProducts();
		return productList;
	}
	
	
	@ApiOperation(value="To get product Resource by product id", response=Product.class,httpMethod="GET",nickname="getProductById")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product is Reterived Successfully by using Id"),
            @ApiResponse(code = 401, message = "You are not authorized user to view the resource"),
            @ApiResponse(code = 403, message = "The resource you trying to access is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not available")
    }
    )
	@RequestMapping(value = "/{id}", method= RequestMethod.GET, produces = "application/json")
	public Product getProductById(@PathVariable(value="id") Integer id)throws ProductException{
		logger.info("product id to return"+id);
		Product product = productService.getProductById(id);
		if(null==product || product.getId()<=0) {
			throw new ProductException("product doesnot exist");
		}
		return product;
		
	}
	
	@ApiOperation(value="To save Product details", response=String.class,httpMethod="POST",nickname="saveProduct")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "product saved Successfully"),
            @ApiResponse(code = 401, message = "You are not authorized user to view the resource"),
            @ApiResponse(code = 403, message = "The resource you trying to access is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not available")
    }
    )
	@RequestMapping(value = "/add", method= RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> saveProduct(@Valid @RequestBody Product product) {
		productService.saveProduct(product);
		return new ResponseEntity<String>("product Saved successfully", HttpStatus.OK);
    
	}
	
	@ApiOperation(value="To delete product Resource", response=String.class,httpMethod="DELETE",nickname="deleteProductById")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "product deleted successfully"),
            @ApiResponse(code = 401, message = "You are not authorized user to view the resource"),
            @ApiResponse(code = 403, message = "The resource you trying to access is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not available")
    }
    )
	@RequestMapping(value = "/{id}", method= RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Response> deleteProductById(@PathVariable(value="id") Integer id) throws ProductException {
		Product product = productService.getProductById(id);
		if(null==product || product.getId()<=0) {
			throw new ProductException("product doesnot exist");
		}
		productService.deleteProduct(id);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),"product deleted successfully") ,HttpStatus.OK);
	}
	
	@ApiOperation(value="To update product Resource", response=String.class,httpMethod="PUT",nickname="UpdateProduct")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "product updated successfully"),
            @ApiResponse(code = 401, message = "You are not authorized user to view the resource"),
            @ApiResponse(code = 403, message = "The resource you trying to access is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not available")
    }
    )
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT,produces="application/json")
	public ResponseEntity<String> updateProduct(@PathVariable(value="id") Integer id, @Valid @RequestBody Product product){
		productService.updateProduct(product);
		return new ResponseEntity<String>("product updated successfully", HttpStatus.OK);
	}
	
	
}
