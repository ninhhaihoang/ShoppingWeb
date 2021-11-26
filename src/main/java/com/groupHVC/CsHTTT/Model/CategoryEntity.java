package com.groupHVC.CsHTTT.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_NAME", nullable = false, unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private Set<ProductEntity> product;

    public CategoryEntity(Long categoryId, String categoryName, Set<ProductEntity> product) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.product = product;
    }

    public CategoryEntity() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<ProductEntity> getProduct() {
        return product;
    }

    public void setProduct(Set<ProductEntity> product) {
        this.product = product;
    }
}
