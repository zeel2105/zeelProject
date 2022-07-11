package com.demo.spring;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.model.Product;
import com.demo.spring.service.ProductService;



@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> list = productService.getAllProduct();
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }
	
	@GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id)                                           {
		Product entity = productService.getProductById(id);
        return new ResponseEntity<Product>(entity, HttpStatus.OK);
    }
	

	@RequestMapping(method=RequestMethod.POST,value="/product")
	 public ResponseEntity<Product> addproduct(@RequestBody Product product) {
		Product entity = productService.createProduct(product);
		return new ResponseEntity<Product>(entity, HttpStatus.OK);
	}
	
	//@RequestMapping(method=RequestMethod.PUT,value="/product/{id}")
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateproduct(@RequestBody Product product,@PathVariable int id) {
		Product entity =productService.updateProduct(product, id);
		return new ResponseEntity<Product>(entity, HttpStatus.OK);
	}

	//@RequestMapping(method=RequestMethod.DELETE,value="/product/{id}")
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Product> deleteproduct(@PathVariable int id) {
		Product entity =productService.deleteProductById(id);
		return new ResponseEntity<Product>(entity, HttpStatus.OK);
	}
	

}
