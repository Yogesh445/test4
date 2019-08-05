package com.fynd.mapper;

import com.fynd.entity.Warehouse;
import com.fynd.request.WarehouseRequest;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper  {

    public Warehouse getWarehouse(WarehouseRequest warehouseRequest) {
        return new DozerBeanMapper().map(warehouseRequest, Warehouse.class);
    }

}
