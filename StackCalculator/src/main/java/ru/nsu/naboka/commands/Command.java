package ru.nsu.naboka.commands;

import ru.nsu.naboka.Context;
import ru.nsu.naboka.exceptions.CalculatorException;

import java.io.BufferedReader;

public interface Command {
    void execute(Context context, String[] args)  throws CalculatorException;
}
