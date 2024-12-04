package com.java.ProductMangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.ProductMangement.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
