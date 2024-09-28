package com.example.inventorymanagement.InventoryManagement.service;

import com.example.inventorymanagement.InventoryManagement.dto.InventoryDTO;
import com.example.inventorymanagement.InventoryManagement.model.Inventory;
import com.example.inventorymanagement.InventoryManagement.repo.InventoryRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<InventoryDTO> getAllInventories() {
        List<Inventory> inventoryList = inventoryRepo.findAll();
        return modelMapper.map(inventoryList, new TypeToken<List<InventoryDTO>>(){}.getType());
    }

    public InventoryDTO saveInventory(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public InventoryDTO updateInventory(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public String deleteInventory(Integer inventoryId) {
        inventoryRepo.deleteById(inventoryId);
        return "Inventory deleted";
    }

    public InventoryDTO getInventory(Integer inventoryId) {
        Inventory inventory = inventoryRepo.getInventoryById(inventoryId);
        return modelMapper.map(inventory, InventoryDTO.class);
    }
}
