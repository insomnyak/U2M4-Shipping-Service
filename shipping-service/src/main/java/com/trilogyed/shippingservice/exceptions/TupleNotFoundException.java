package com.trilogyed.shippingservice.exceptions;

public class TupleNotFoundException extends RuntimeException {
    public TupleNotFoundException() {
    }

    public TupleNotFoundException(String message) {
        super(message);
    }
}
