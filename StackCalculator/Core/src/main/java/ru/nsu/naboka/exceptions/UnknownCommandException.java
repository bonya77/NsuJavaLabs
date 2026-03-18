package ru.nsu.naboka.exceptions;

public class UnknownCommandException extends CommandException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
