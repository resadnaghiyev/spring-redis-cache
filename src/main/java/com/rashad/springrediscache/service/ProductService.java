package com.rashad.springrediscache.service;

import com.rashad.springrediscache.entity.Product;
import com.rashad.springrediscache.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Cacheable("products")
    public List<Product> getAll() {
        System.out.println("...Calling service to get data from DB...");
        return productRepository.findAll();
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Cacheable(key = "#id", value = "products")
    public Product findById(Long id) {
        System.out.println("...Calling service to get one data from DB...");
        return productRepository.findById(id).get();
    }

    @CacheEvict(value = "products", allEntries = true)
    public String delete(Long id) {
        productRepository.deleteById(id);
        return "Product Deleted Successfully!!";
    }

    @CacheEvict(key = "#id", value = "products")
    public Product update(Long id, Product product) {
        Product old_product = productRepository.findById(id).get();
        old_product.setName(product.getName());
        old_product.setPrice(product.getPrice());
        old_product.setDescription(product.getDescription());
        return productRepository.save(old_product);
    }
}
