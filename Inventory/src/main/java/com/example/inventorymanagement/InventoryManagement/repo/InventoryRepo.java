package com.example.inventorymanagement.InventoryManagement.repo;

import com.example.inventorymanagement.InventoryManagement.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
    @Query(value = "SELECT * FROM Inventory WHERE item_id = ?1", nativeQuery = true)
    Inventory getInventoryById(Integer inventoryId);
}
