package com.demo.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{
}

