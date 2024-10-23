package com.shopstyle.ms_catalog.repository;

import com.shopstyle.ms_catalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
