package com.groupHVC.CsHTTT.Service;

import com.groupHVC.CsHTTT.Model.CategoryEntity;
import com.groupHVC.CsHTTT.Model.ProductEntity;
import com.groupHVC.CsHTTT.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductEntity> getProducts() {
       return productRepository.findAll();
    }

    public ProductEntity getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    public List<ProductEntity> getProductsByCate(CategoryEntity category) {
        return productRepository.findAllByCategory(category);
    }

    public ProductEntity updateProduct(Long id, ProductEntity product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
