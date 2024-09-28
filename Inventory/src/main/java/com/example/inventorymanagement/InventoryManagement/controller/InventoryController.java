package com.example.inventorymanagement.InventoryManagement.controller;

import com.example.inventorymanagement.InventoryManagement.dto.InventoryDTO;
import com.example.inventorymanagement.InventoryManagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/")
    public List<InventoryDTO> getInventories() {
        return inventoryService.getAllInventories();
    }

    @PostMapping("/addinventory")
    public InventoryDTO addInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.saveInventory(inventoryDTO);
    }

    @PutMapping("/updateinventory")
    public InventoryDTO editInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateInventory(inventoryDTO);
    }

    @DeleteMapping("/deleteinventory/{inventoryId}")
    public String removeInventory(@PathVariable Integer inventoryId) {
        return inventoryService.deleteInventory(inventoryId);
    }

    @GetMapping("/inventory/{inventoryId}")
    public InventoryDTO getInventory(@PathVariable Integer inventoryId) {
        return inventoryService.getInventory(inventoryId);
    }
}
