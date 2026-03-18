package ru.nsu.naboka;

import ru.nsu.naboka.exceptions.CalculatorException;

public class Main {
    public static void main(String[] args) throws  CalculatorException{
        Calculator calculator = new Calculator();

        Reader reader = new Reader(args);

        calculator.execute(reader);

    }
}
