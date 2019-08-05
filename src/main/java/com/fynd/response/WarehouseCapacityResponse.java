package com.fynd.response;

import com.fynd.entity.WarehouseItems;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WarehouseCapacityResponse {

    String warehouseName;
    int capacity;
    int current_items_holdes;
    List<WarehouseItems> warehouseItems;
}
