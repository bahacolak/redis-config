package com.bahadircolak.redisapp.repository;

import com.bahadircolak.redisapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
