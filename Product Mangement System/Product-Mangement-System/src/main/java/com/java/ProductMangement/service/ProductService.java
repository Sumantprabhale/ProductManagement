package com.java.ProductMangement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java.ProductMangement.entity.Product;
import com.java.ProductMangement.repository.ProductRepository;



@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Page<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

}
