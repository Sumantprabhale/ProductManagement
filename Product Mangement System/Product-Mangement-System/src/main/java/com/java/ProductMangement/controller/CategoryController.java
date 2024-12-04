package com.java.ProductMangement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java.ProductMangement.entity.Category;
import com.java.ProductMangement.service.CategoryService;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public Page<Category> getAllCategories(@RequestParam(defaultValue = "0") int page) {
		return categoryService.getAllCategories(PageRequest.of(page, 10));
	}

	@PostMapping
	public Category createCategory(@RequestBody Category category) {
		return categoryService.saveCategory(category);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
		Optional<Category> category = categoryService.getCategoryById(id);
		return category.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
		return categoryService.getCategoryById(id).map(category -> {
			category.setName(updatedCategory.getName());
			category.setDescription(updatedCategory.getDescription());
			categoryService.saveCategory(category);
			return ResponseEntity.ok(category);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		if (categoryService.getCategoryById(id).isPresent()) {
			categoryService.deleteCategory(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}