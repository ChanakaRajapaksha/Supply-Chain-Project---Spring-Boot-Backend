package com.ordermanagement.OrderManagement.repo;

import com.ordermanagement.OrderManagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT * FROM `order` WHERE id = ?1", nativeQuery = true)
    Order getOrderById(Integer orderId);
}
