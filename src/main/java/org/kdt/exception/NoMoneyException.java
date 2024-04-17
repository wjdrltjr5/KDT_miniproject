package org.kdt.exception;

public class NoMoneyException extends Exception{
    public NoMoneyException() {
        super();
    }

    public NoMoneyException(String message) {
        super(message);
    }
}
