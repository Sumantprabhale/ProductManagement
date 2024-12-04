package com.java.ProductMangement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ProductMangement.entity.Category;
import com.java.ProductMangement.repository.CategoryRepository;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Page<Category> getAllCategories(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Optional<Category> getCategoryById(Long id) {
		return categoryRepository.findById(id);
	}

	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}
}
