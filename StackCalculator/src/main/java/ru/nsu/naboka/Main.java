package ru.nsu.naboka;

import ru.nsu.naboka.exceptions.CalculatorException;
import ru.nsu.naboka.commands.CommandFabric;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.*;
import java.nio.Buffer;
import ru.nsu.naboka.Reader;

public class Main {
    public static void main(String[] args) throws  CalculatorException{
        Calculator calculator = new Calculator();

        Reader reader = new Reader(args);

        calculator.execute(reader);

    }
}
