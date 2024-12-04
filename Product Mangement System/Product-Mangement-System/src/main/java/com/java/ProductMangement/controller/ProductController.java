package com.java.ProductMangement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java.ProductMangement.entity.Product;
import com.java.ProductMangement.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

//	@GetMapping
//	public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page) {
//		return productService.getAllProducts(PageRequest.of(page, 10));
//	}

	@GetMapping
	public Page<Product> getAllProducts(@RequestParam(name = "page", defaultValue = "0") int page) {
		return productService.getAllProducts(PageRequest.of(page, 10));
	}

	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(name = "id") Long id) {
		Optional<Product> product = productService.getProductById(id);
		return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
		return productService.getProductById(id).map(product -> {
			product.setName(updatedProduct.getName());
			product.setPrice(updatedProduct.getPrice());
			product.setCategory(updatedProduct.getCategory());
			productService.saveProduct(product);
			return ResponseEntity.ok(product);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		if (productService.getProductById(id).isPresent()) {
			productService.deleteProduct(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}