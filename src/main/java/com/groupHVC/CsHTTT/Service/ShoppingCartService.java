package com.groupHVC.CsHTTT.Service;

import com.groupHVC.CsHTTT.Model.CartItemEntity;
import com.groupHVC.CsHTTT.Model.ProductEntity;
import com.groupHVC.CsHTTT.Model.UserEntity;
import com.groupHVC.CsHTTT.Repository.CartItemRepository;
import com.groupHVC.CsHTTT.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItemEntity> listCartItems(UserEntity user) {
        return cartItemRepository.findByUser(user);
    }

    public Integer addProduct(Long productId, Integer quantity, UserEntity user) {
        Integer addedQuantity = quantity;

        ProductEntity product = productRepository.findById(productId).get();

        CartItemEntity cartItem = cartItemRepository.findByUserAndProduct(user, product);

        if (cartItem != null) {
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        } else {
            cartItem = new CartItemEntity();
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product);
            cartItem.setUser(user);
        }

        cartItemRepository.save(cartItem);

        return  addedQuantity;
    }
}
