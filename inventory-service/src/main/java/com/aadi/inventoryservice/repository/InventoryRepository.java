package com.aadi.inventoryservice.repository;

import com.aadi.inventoryservice.model.Inventory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
  Optional<Inventory> findBySkuCode();
}
