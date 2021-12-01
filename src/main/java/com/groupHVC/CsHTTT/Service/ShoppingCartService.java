package com.groupHVC.CsHTTT.Service;

import com.groupHVC.CsHTTT.Model.CartItemEntity;
import com.groupHVC.CsHTTT.Model.OrderStatusEntity;
import com.groupHVC.CsHTTT.Model.ProductEntity;
import com.groupHVC.CsHTTT.Model.UserEntity;
import com.groupHVC.CsHTTT.Repository.CartItemRepository;
import com.groupHVC.CsHTTT.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItemEntity> listCartItems(UserEntity user) {
        return cartItemRepository.findByCustomer(user);
    }

    public CartItemEntity cartItem(Long id) {
        return cartItemRepository.findById(id).get();
    }

    public List<CartItemEntity> listHistoryItems(UserEntity user) {
        return cartItemRepository.findByUser(user);
    }

    public List<CartItemEntity> listOrder() {
        return cartItemRepository.findAll();
    }

    public Integer addProduct(Long productId, Integer quantity, UserEntity user, OrderStatusEntity status) {
        Integer addedQuantity = quantity;

        ProductEntity product = productRepository.findById(productId).get();

        CartItemEntity cartItem = cartItemRepository.findByUserAndProduct(user, product);

        if (cartItem != null) {
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
            cartItem.setStatus(status);
        } else {
            cartItem = new CartItemEntity();
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product);
            cartItem.setUser(user);
            cartItem.setStatus(status);
        }

        cartItemRepository.save(cartItem);

        return  addedQuantity;
    }

    public Long updateQuantity(Long productId, Integer quantity, UserEntity user ) {
        cartItemRepository.updateQuantity(quantity, productId, user.getUserId());
        ProductEntity product = productRepository.findById(productId).get();
        Long subtotal = product.getProductPrice() * quantity;
        return subtotal;
    }

    public void confirmCartItem(UserEntity user) {
        cartItemRepository.confirmCartItem(user.getUserId());
    }

    public void updateOrder(CartItemEntity item) {
        cartItemRepository.save(item);
    }

    public void removeProduct(Long productId, UserEntity user) {
        cartItemRepository.deleteByUserAndProduct(user.getUserId(), productId);
    }
}
