package com.demo.Spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.spring.model.Product;
import com.demo.spring.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class ProductControllerTest  extends SpringBootPostgresApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;
	
	@Test
	public void testGetProductById() throws Exception {
		
		Product product = new Product();
		product.setId(1001);
		product.setName("HP LAPTOP");
		product.setDescription("Hp new laptop");
		product.setManufacturerName("HP");
		product.setPrice(55000.25);
		//Optional<Product> pro  =Optional.of(product);
		
		Mockito.when(productService.getProductById(Mockito.anyInt())).thenReturn(product);
		
		String URI = "/product/1001";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(product);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	

	 //Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	
	  @Test
	  @Order(2) 
	  public void testCreateProduct() throws Exception 
	  {
	  
	  Product product = new Product(); 
	  product.setId(1003);
	  product.setName("Lenovo LAPTOP");
	  product.setDescription("Lenovo new laptop");
	  product.setManufacturerName("Lenovo"); 
	  product.setPrice(55000.25);
	  
	  String inputInJson = this.mapToJson(product);
	  
	  String URI = "/product";
	  
	  Mockito.when(productService.createProduct(Mockito.any(Product.class))).
	  thenReturn(product);
	  
	  RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
	  .accept(MediaType.APPLICATION_JSON).content(inputInJson)
	  .contentType(MediaType.APPLICATION_JSON);
	  
	  MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	  MockHttpServletResponse response = result.getResponse();
	  
	  String outputInJson = response.getContentAsString();
	  
	  assertThat(outputInJson).isEqualTo(inputInJson);
	  assertEquals(HttpStatus.OK.value(), response.getStatus()); 
	  }
	  
	  @Test
	  @Order(3) 
	  public void testGetAllProducts() throws Exception {
	  
		  Product product1 = new Product(); 
		  product1.setId(1008);
		  product1.setName("Surface LAPTOP");
		  product1.setDescription("Samsung new laptop");
		  product1.setManufacturerName("Samsung"); product1.setPrice(55000.25);
		  
		  Product product2 = new Product(); 
		  product2.setId(1009);
		  product2.setName("Ipad"); product2.setDescription("Apple new laptop");
		  product2.setManufacturerName("Apple"); product2.setPrice(55000.25);
		  
		  List<Product> productList = new ArrayList<>(); productList.add(product1);
		  productList.add(product2);
		  
		  Mockito.when(productService.getAllProduct()).thenReturn(productList);
		  
		  String URI = "/products"; RequestBuilder requestBuilder =
		  MockMvcRequestBuilders.get( URI).accept( MediaType.APPLICATION_JSON);
		  
		  MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		  
		  String expectedJson = this.mapToJson(productList); String outputInJson =
		  result.getResponse().getContentAsString();
		  assertThat(outputInJson).isEqualTo(expectedJson); 
		  
	  }
	 
	
	  @Test 
	  public void testdeleteProduct() throws Exception 
	  { 
		  String uri ="/product/1008";
		  Product product1 = new Product(); 
		  product1.setId(1008);
		  product1.setName("Surface LAPTOP");
		  product1.setDescription("Samsung new laptop");
		  product1.setManufacturerName("Samsung"); 
		  product1.setPrice(55000.25);
		  String inputInJson = this.mapToJson(product1);
		  
		  Mockito.when(productService.deleteProductById(Mockito.anyInt())).thenReturn(product1);
		  MvcResult mvcResult =
		  mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn(); 
		  
		  String outputInJson = mvcResult.getResponse().getContentAsString();
		  assertThat(outputInJson).isEqualTo(inputInJson);
		  assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus()); 
		  
	  }
	  
		
		  @Test 
		  public void updateProduct() throws Exception 
		  { 
		  String uri ="/product/2"; 
		  
		  Product product3 = new Product(); 
		  product3.setName("Ipad2");
		  product3.setDescription("Apple new laptop");
		  product3.setManufacturerName("Apple"); 
		  product3.setPrice(55000.25);
		  
		  String inputJson = this.mapToJson(product3); 
		  
		  Mockito.when(productService.updateProduct(Mockito.any(Product.class),Mockito.anyInt())).thenReturn(product3);
		  
		  MvcResult mvcResult =
		  mockMvc.perform(MockMvcRequestBuilders.put(uri)
		  .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		  
		  assertThat( mvcResult.getResponse().getContentAsString()).isEqualTo(inputJson);
		  assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus()); 
		  }
	 
	
}
