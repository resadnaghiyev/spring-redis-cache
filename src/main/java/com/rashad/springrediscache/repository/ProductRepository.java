package com.rashad.springrediscache.repository;

import com.rashad.springrediscache.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
