package me.pierrontmaxi.rover.domain.exception;

public class OutOfPlateauException extends RuntimeException {
    public OutOfPlateauException(String message){
        super(message);
    }
}
