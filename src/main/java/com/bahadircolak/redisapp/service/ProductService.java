package com.bahadircolak.redisapp.service;

import com.bahadircolak.redisapp.model.Product;
import com.bahadircolak.redisapp.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(cacheNames = "products", key = "#root.methodName")
    public List<Product> getAllProducts() throws InterruptedException {
        return productRepository.findAll();
    }

    @Cacheable(cacheNames = "products", key = "#id")
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @CachePut(cacheNames = "products", key = "#product.id")
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Cacheable(cacheNames = "mySpecialCache")
    public String longRunnigMethod() throws InterruptedException {
        Thread.sleep(5000L);
        return "method calisti";
    }

    @CacheEvict(cacheNames = "mySpecialCache")
    public void clearCache(){
        System.out.println("cache temizledi");
    }
}
