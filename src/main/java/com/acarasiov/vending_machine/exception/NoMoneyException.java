package com.acarasiov.vending_machine.exception;

public class NoMoneyException extends Exception{
    public NoMoneyException(String message) {
        super(message);
    }

    public NoMoneyException(String message, Throwable cause) {
        super(message, cause);
    }
}
