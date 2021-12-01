package com.groupHVC.CsHTTT.Repository;

import com.groupHVC.CsHTTT.Model.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Integer> {
}
