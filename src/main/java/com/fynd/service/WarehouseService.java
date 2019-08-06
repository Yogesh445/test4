package com.fynd.service;

import com.fynd.entity.Warehouse;
import com.fynd.entity.WarehouseItems;
import com.fynd.exception.WarehouseAlreadyPresentException;
import com.fynd.exception.WarehouseDoesNotHaveSpace;
import com.fynd.exception.WarehouseNotFoundException;
import com.fynd.mapper.WarehouseItemsMapper;
import com.fynd.mapper.WarehouseMapper;
import com.fynd.reposiotory.WarehouseItemsRepository;
import com.fynd.reposiotory.WarehouseRepository;
import com.fynd.request.WarehouseItemRequest;
import com.fynd.request.WarehouseRequest;
import com.fynd.response.WarehouseCapacityResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class WarehouseService {
    final WarehouseRepository warehouseRepository;
    final WarehouseMapper warehouseMapper;
    final WarehouseItemsMapper warehouseItemsMapper;
    final WarehouseItemsRepository warehouseItemsRepository;

    public Warehouse addWarehouseCapacity(WarehouseRequest request) throws WarehouseAlreadyPresentException {
        Warehouse existingWarehouse = warehouseRepository.findByName(request.getName());
        if(existingWarehouse!=null){
            String errorMsg = String.format("Warehouse with name %s is already registered.Please changes the name.", request.getName());
            throw new WarehouseAlreadyPresentException(errorMsg);
        }

        Warehouse warehouse = warehouseMapper.getWarehouse(request);
        warehouse.setId((long) Math.random() * 1000000000000L);

        return warehouseRepository.save(warehouse);
    }

    public WarehouseItems addItem(WarehouseItemRequest request) throws WarehouseNotFoundException, WarehouseDoesNotHaveSpace {

        Warehouse warehouse = warehouseRepository.findByName(request.getWarehouseName());
        if (warehouse == null) {
            String errorMsg = String.format("Warehouse is not present into DB with given Name %s", warehouse.getName());
            throw new WarehouseNotFoundException(errorMsg);
        }
        if (warehouse.getCapacity() == warehouse.getCurrentItems()) {
            String errorMsg = String.format("Warehouse name: %s has been completely full ", warehouse.getName());
            throw new WarehouseDoesNotHaveSpace(errorMsg);
        }
        warehouse.setCurrentItems(warehouse.getCurrentItems() + 1);
        WarehouseItems warehouseItems = warehouseItemsMapper.getWarehouseItems(request);
        warehouseItems.setId(new Random().nextLong());
        warehouseItems.setWarehouse(warehouse);
        warehouseRepository.save(warehouse);
        return warehouseItemsRepository.save(warehouseItems);
    }

    public WarehouseCapacityResponse checkCapacity(String warehouseName) throws WarehouseNotFoundException {

        Warehouse warehouse = warehouseRepository.findByName(warehouseName);
        List<WarehouseItems> warehouseItems = warehouseItemsRepository.findByWarehouseId(warehouse.getId());
        if (warehouse == null) {
            String errorMsg = String.format("Warehouse is not present into DB with given Name %s", warehouse.getName());
            throw new WarehouseNotFoundException(errorMsg);
        }
        WarehouseCapacityResponse warehouseCapacityResponse = new WarehouseCapacityResponse();
        warehouseCapacityResponse.setCapacity(warehouse.getCapacity());
        warehouseCapacityResponse.setCurrent_items_holdes(warehouse.getCurrentItems());
        warehouseCapacityResponse.setWarehouseName(warehouse.getName());

        warehouseCapacityResponse.setWarehouseItems(warehouseItems);
        return warehouseCapacityResponse;

    }

    public WarehouseItems findItem(Long itemId) throws WarehouseNotFoundException {

        WarehouseItems warehouseItems = warehouseItemsRepository.findById(itemId);
        if (warehouseItems == null) {
            String errorMsg = String.format("Warehouse does not contain item based on Id %s", itemId);
            throw new WarehouseNotFoundException(errorMsg);
        }
        return warehouseItems;

    }

}
