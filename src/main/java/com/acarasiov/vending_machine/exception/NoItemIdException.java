package com.acarasiov.vending_machine.exception;

public class NoItemIdException extends Exception{
    public NoItemIdException(String message) {
        super(message);
    }

    public NoItemIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
