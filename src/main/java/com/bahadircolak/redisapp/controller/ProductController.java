package com.bahadircolak.redisapp.controller;

import com.bahadircolak.redisapp.model.Product;
import com.bahadircolak.redisapp.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private int sayac = 0;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() throws InterruptedException {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/control")
    public String cacheControl() throws InterruptedException {
        if (sayac == 5){
            productService.clearCache();
            sayac = 0;
        }
        sayac++;
        return productService.longRunnigMethod();
    }
}
