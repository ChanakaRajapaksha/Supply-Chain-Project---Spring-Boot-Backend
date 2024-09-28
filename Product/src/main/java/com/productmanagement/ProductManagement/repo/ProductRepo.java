package com.productmanagement.ProductManagement.repo;

import com.productmanagement.ProductManagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM Product WHERE id = ?1", nativeQuery = true)
    Product getProductById(Integer productId);
}
