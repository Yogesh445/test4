package com.fynd.mapper;

import com.fynd.entity.WarehouseItems;
import com.fynd.request.WarehouseItemRequest;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

@Component
public class WarehouseItemsMapper  {
    Mapper mapper = new DozerBeanMapper();

    public WarehouseItems getWarehouseItems(WarehouseItemRequest warehouseItemRequest) {
        return mapper.map(warehouseItemRequest, WarehouseItems.class);
    }
}
