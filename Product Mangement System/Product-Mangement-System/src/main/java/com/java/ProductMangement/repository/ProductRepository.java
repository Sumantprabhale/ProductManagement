package com.java.ProductMangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.ProductMangement.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
