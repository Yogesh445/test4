package com.fynd.exception;

public class WarehouseAlreadyPresentException extends Exception {
    public WarehouseAlreadyPresentException() {
    }

    public WarehouseAlreadyPresentException(String message) {
        super(message);
    }

    public WarehouseAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }
}
