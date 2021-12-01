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
    @Query("SELECT c FROM CartItemEntity c WHERE c.user = ?1 " +
            "AND c.status = '1'")
    @Modifying
    List<CartItemEntity> findByCustomer(UserEntity user);

    List<CartItemEntity> findByUser(UserEntity user);

    CartItemEntity findByUserAndProduct(UserEntity user, ProductEntity product);

    @Query("UPDATE CartItemEntity c SET c.quantity = ?1 WHERE c.product.productId = ?2 "
    + "AND c.user.userId = ?3")
    @Modifying
    void updateQuantity(Integer quantity, Long productId, Long userId);

    @Query("UPDATE CartItemEntity ci SET ci.status = 2" +
            "WHERE ci.user.userId = ?1 AND ci.status.id = 1")
    @Modifying
    void confirmCartItem(Long userId);

    @Query("DELETE FROM CartItemEntity c WHERE c.user.userId = ?1 AND c.product.productId = ?2"
            + "AND c.status = '1'")
    @Modifying
    void deleteByUserAndProduct (Long userId, Long productId);
}
