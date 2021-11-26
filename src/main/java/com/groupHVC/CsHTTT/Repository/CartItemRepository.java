package com.groupHVC.CsHTTT.Repository;

import com.groupHVC.CsHTTT.Model.CartItemEntity;
import com.groupHVC.CsHTTT.Model.ProductEntity;
import com.groupHVC.CsHTTT.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    List<CartItemEntity> findByUser(UserEntity user);

    CartItemEntity findByUserAndProduct(UserEntity user, ProductEntity product);

    @Query("UPDATE CartItemEntity c SET c.quantity = ?1 WHERE c.product.productId = ?2 "
    + "AND c.user.userId = ?3")
    @Modifying
    void updateQuantity(Integer quantity, Long productId, Long userId);
}
