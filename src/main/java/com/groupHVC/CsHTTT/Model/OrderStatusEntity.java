package com.groupHVC.CsHTTT.Model;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_STATUS")
public class OrderStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATUS_ID")
    private int id;

    @Column(name = "STATUS")
    private String status;

    public OrderStatusEntity(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public OrderStatusEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

