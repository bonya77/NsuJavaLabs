package ru.nsu.naboka;

import ru.nsu.naboka.exceptions.CalculatorException;

import java.util.List;

public class Calculator {
    private final Context context = new Context();
    private final CommandFactory commandFactory;

    public Calculator() {
        this.commandFactory = new CommandFactory();
    }

    public void execute(Reader reader) {
        try {
            while (reader.readNextLine()) {
                List<String> parts = reader.getParts();
                if (parts.isEmpty() || parts.getFirst().startsWith("#")) {
                    continue;
                }

                String commandName = parts.getFirst();

                try {
                    Command command = commandFactory.CreateCommand(commandName);
                    command.execute(context, parts);
                } catch (CalculatorException e) {
                    System.out.println("Cannot execute command: " + e.getMessage());
                }
            }
        } catch (CalculatorException e) {
            System.out.println(e.getMessage());
        }
    }
}