package com.groupHVC.CsHTTT.Model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table (name = "CART_ITEMS")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Column(name = "QUANTITY")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID", columnDefinition = "int default 1")
    private OrderStatusEntity status;


    public CartItemEntity(Long id, ProductEntity product, UserEntity user, int quantity, OrderStatusEntity status) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.quantity = quantity;
        this.status = status;
    }

    public CartItemEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderStatusEntity getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEntity status) {
        this.status = status;
    }

    @Transient
    public Long getSubtotal(){
        return this.product.getProductPrice() * quantity;
    }
}
