package com.demo.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.dao.ProductDao;
import com.demo.spring.model.Product;



@Service
public class ProductService  {
	
	
	
	@Autowired
	
	 ProductDao productdao;
	
	
	public List<Product> getAllProduct() {
		List<Product> productList = productdao.findAll();
       
       if(productList.size() > 0) {
           return productList ;
       } else {
           return new ArrayList<Product>();
       }
		
	}


	 public Product getProductById(int id)  
	    {
	        Optional<Product> product = productdao.findById(id);
	         
	        if( product.isPresent()) {
	            return  product.get();
	         
	        }
	        else
	        {
	        	return new Product();
	        }
	    }

		 public Product updateProduct(Product product1, int id) 
		    {
		        Optional<Product> product = productdao.findById(product1.getId());
		        Product newEntity =null;
		        if(product.isPresent()) 
		        {
		        	newEntity= product.get();
		            newEntity.setId(product1.getId());
		            newEntity.setName(product1.getName());
		            newEntity.setDescription(product1.getDescription());
		            newEntity.setManufacturerName(product1.getManufacturerName());
		            newEntity.setPrice(product1.getPrice());
		 
		            newEntity = productdao.save(newEntity);
		        }
		             
		            return newEntity;
		    } 
		 
		 public Product createProduct(Product product1)
		 {
			 productdao.save(product1);
			 
			 return product1;
		 }
		 public Product deleteProductById(int id) 
		    {
		        Optional<Product> product = productdao.findById(id);
		         
		        if(product.isPresent()) 
		        {
		        	productdao.deleteById(id);
		        }
		           return  product.get();
		    }
		 
	
		 
		    } 
		
    
	


