package com.demo.Spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.spring.dao.ProductDao;
import com.demo.spring.model.Product;
import com.demo.spring.service.ProductService;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
public class ProductServiceTest extends SpringBootPostgresApplicationTests {

	@Autowired
	private ProductService productService;
	
	@MockBean
	private ProductDao productdao;
	
	@Test
	public void testGetProductById(){
		
		Product product = new Product();
		product.setId(1001);
		product.setName("HP LAPTOP");
		product.setDescription("Hp new laptop");
		product.setManufacturerName("HP");
		product.setPrice(55000.25);
		Optional<Product> pro  =Optional.of(product);
		
	    Mockito.when(productdao.findById(1001)).thenReturn(pro);
	    assertThat(productService.getProductById(1001)).isEqualTo(product);
	}
	
	@Test
	public void testGetAllProduct(){
		Product product = new Product();
		product.setId(1001);
		product.setName("HP LAPTOP");
		product.setDescription("Hp new laptop");
		product.setManufacturerName("HP");
		product.setPrice(55000.25);
		
		Product product2 = new Product();
		product2.setId(1002);
		product2.setName("DELL LAPTOP");
		product2.setDescription("Dell new laptop");
		product2.setManufacturerName("DELL");
		product2.setPrice(56000.25);
		
		List<Product> productList = new ArrayList<>();
		productList.add(product);
		productList.add(product2);
		
		Mockito.when(productdao.findAll()).thenReturn(productList);

		assertThat(productService.getAllProduct()).isEqualTo(productList);
		
	}
	
	@Test
	public void testDeleteProductById(){
		Product product = new Product();
		product.setId(1001);
		product.setName("HP LAPTOP");
		product.setDescription("Hp new laptop");
		product.setManufacturerName("HP");
		product.setPrice(55000.25);
		Optional<Product> pro  =Optional.of(product);
		Mockito.when(productdao.findById(1001)).thenReturn(pro);
	    Mockito.when(productdao.existsById(1001)).thenReturn(false);
	    assertThat(productdao.existsById(product.getId())).isEqualTo(false);
	}
	
	
	  @Test 
	  public void testCreateProduct()
	  {
		  Product product2 = new Product(); 
		  product2.setId(1089);
		  product2.setName("HP LAPTOP3"); 
		  product2.setDescription("Hp new laptop3");
		  product2.setManufacturerName("HP3"); 
		  product2.setPrice(56000.25);
		  
		  Mockito.when(productdao.save(product2)).thenReturn(product2);
		  assertThat(productService.createProduct(product2)).isEqualTo(product2);
	  }
	 
	@Test
	public void testUpdateProduct(){
		Product product1 = new Product();
		product1.setId(2);
		product1.setName("Dell Laptop");
		product1.setDescription("Dell new laptop3");
		product1.setManufacturerName("Dell");
		product1.setPrice(56000.25);
		Optional<Product> pro  =Optional.of(product1);
		
		Mockito.when(productdao.findById(2)).thenReturn(pro);
		
		
		Mockito.when(productdao.save(product1)).thenReturn(product1);
		
		assertThat(productService.updateProduct(product1,2)).isEqualTo(product1);
		
	}
	
}
