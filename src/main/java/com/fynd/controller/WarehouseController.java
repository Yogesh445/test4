package com.fynd.controller;

import com.fynd.exception.WarehouseDoesNotHaveSpace;
import com.fynd.exception.WarehouseNotFoundException;
import com.fynd.request.WarehouseItemRequest;
import com.fynd.request.WarehouseRequest;
import com.fynd.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class WarehouseController {
    final WarehouseService warehouseService;

    /**
     * First API to execute and insert the capacity of warehouse
     *
     * @param warehouseRequest
     * @return: Warehouse
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addCapacity")
    public ResponseEntity<?> addCapacity(@RequestBody WarehouseRequest warehouseRequest) {
        return new ResponseEntity<>(this.warehouseService.addWarehouseCapacity(warehouseRequest), HttpStatus.OK);
    }

    /**
     * Second API to insert the data into the warehouse.
     * @param warehouseItemRequest
     * @return
     * @throws WarehouseDoesNotHaveSpace
     * @throws WarehouseNotFoundException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addItem")
    public ResponseEntity<?> addItem(@RequestBody WarehouseItemRequest warehouseItemRequest) throws WarehouseDoesNotHaveSpace, WarehouseNotFoundException {
        return new ResponseEntity<>(this.warehouseService.addItem(warehouseItemRequest), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkCapacityWithItemList")
    public ResponseEntity<?> checkCapacity(@RequestParam("warehouseName") String warehouseName) throws WarehouseDoesNotHaveSpace, WarehouseNotFoundException {
        return new ResponseEntity<>(this.warehouseService.checkCapacity(warehouseName), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findItem")
    public ResponseEntity<?> findItem(@RequestParam("itemId") Long itemId) throws WarehouseDoesNotHaveSpace, WarehouseNotFoundException {
        return new ResponseEntity<>(this.warehouseService.findItem(itemId), HttpStatus.OK);
    }


}
