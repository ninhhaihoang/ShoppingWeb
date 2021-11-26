package com.groupHVC.CsHTTT.Repository;

import com.groupHVC.CsHTTT.Model.CartItemEntity;
import com.groupHVC.CsHTTT.Model.ProductEntity;
import com.groupHVC.CsHTTT.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    public List<CartItemEntity> findByUser(UserEntity user);

    public CartItemEntity findByUserAndProduct(UserEntity user, ProductEntity product);
}
