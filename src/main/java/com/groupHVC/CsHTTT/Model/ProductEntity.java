package com.groupHVC.CsHTTT.Model;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME", nullable = false, unique = true)
    private String productName;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private Long productPrice;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "PRODUCT_DESCRIPTION", nullable = false, length = 512)
    private String productDescription;

    @Column(name = "MAIN_PICTURE", nullable = false)
    private String mainPicture;

    @Column(name = "PICTURE1")
    private String picture1;

    @Column(name = "PICTURE2")
    private String picture2;

    @Column(name = "PICTURE3")
    private String picture3;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryEntity category;

    public ProductEntity(Long productId, String productName, Long productPrice, int quantity, String productDescription, String mainPicture, String picture1, String picture2, String picture3, CategoryEntity category) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.productDescription = productDescription;
        this.mainPicture = mainPicture;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
        this.category = category;
    }

    public ProductEntity() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture;
    }

    @Transient
    public String getMainPicPath() {
        if (mainPicture == null || productId == null) return null;

        return "/product-mainPicture/" + productId + "/" + mainPicture;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
