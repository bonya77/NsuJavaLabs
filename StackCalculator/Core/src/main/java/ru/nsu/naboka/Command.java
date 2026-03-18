package ru.nsu.naboka;

import ru.nsu.naboka.Context;
import ru.nsu.naboka.exceptions.CalculatorException;

import java.util.List;

public interface Command {
    void execute(Context context, List<String> args)  throws CalculatorException;
}
