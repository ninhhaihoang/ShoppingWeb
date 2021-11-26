package com.groupHVC.CsHTTT.Repository;

import com.groupHVC.CsHTTT.Model.CategoryEntity;
import com.groupHVC.CsHTTT.Model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByCategory(CategoryEntity category);
}
