package com.fynd.reposiotory;

import com.fynd.entity.WarehouseItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseItemsRepository extends JpaRepository<WarehouseItems, Integer> {
    List<WarehouseItems> findByWarehouseId(Long id);
    WarehouseItems findById(Long id);
}
