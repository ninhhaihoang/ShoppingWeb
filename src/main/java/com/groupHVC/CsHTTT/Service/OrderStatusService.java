package com.groupHVC.CsHTTT.Service;

import com.groupHVC.CsHTTT.Model.OrderStatusEntity;
import com.groupHVC.CsHTTT.Repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService {
    @Autowired
    OrderStatusRepository statusRepository;

    public OrderStatusEntity getStatus(int id) {
        return statusRepository.findById(id).get();
    }

    public List<OrderStatusEntity> getAllStatus() {
        return statusRepository.findAll();
    }
}
