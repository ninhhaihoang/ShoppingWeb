package com.groupHVC.CsHTTT.Repository;

import com.groupHVC.CsHTTT.Model.CategoryEntity;
import com.groupHVC.CsHTTT.Model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByCategory(CategoryEntity category);

    @Query("SELECT  p FROM ProductEntity p ORDER BY p.productId DESC")
    List<ProductEntity> findNewProduct();

    @Query(value = "select * from finaldb.products order by finaldb.products.PRODUCT_ID desc limit 5;", nativeQuery = true)
    List<ProductEntity> newProduct();

    @Query(value = "select * from finaldb.products limit 5;", nativeQuery = true)
    List<ProductEntity> suggestProduct();

    @Query("SELECT p FROM ProductEntity p WHERE p.productName LIKE %?1%")
    List<ProductEntity> searchProduct(String keyword);

    @Query("UPDATE ProductEntity p SET p.productName = ?1, p.quantity = ?2, p.category = ?3, p.productDescription = ?4, " +
            "p.productPrice = ?5 WHERE p.productId = ?6 ")
    void updateProduct(String productName, int quantity, Long categoryId, String description, Long price,Long productId);
}
