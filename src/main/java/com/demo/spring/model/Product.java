package com.demo.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")

public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="productid")
	private int id;
	
	@Column(name="productprice")
	private double price;
	
	@Column(name="productname")
	private String name;
	
	@Column(name="productdesc")
	private String description;
	
	@Column(name="productmfg")
	private String manufacturerName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + ", name=" + name + ", description=" + description
				+ ", manufacturerName=" + manufacturerName + "]";
	}
	public Product(int id, double price, String name, String description, String manufacturerName) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
		this.description = description;
		this.manufacturerName = manufacturerName;
	}
	
	public Product () {
		
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	
	
	
	

}
