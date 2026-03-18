package ru.nsu.naboka;

import ru.nsu.naboka.commands.CommandFactory;
import ru.nsu.naboka.exceptions.CalculatorException;

import java.util.List;


public class Calculator {
    private final Context context = new Context();
    private final CommandFactory fabric = new CommandFactory();

    void execute(Reader reader) {
        try {
            while (reader.readNextLine()) {
                List<String> parts = reader.getParts();
                if (parts.getFirst().startsWith("#") || parts.getFirst().isEmpty()) {
                    continue;
                }
                String commandName = parts.getFirst();
                Command command = null;
                try {
                    command = fabric.CreateCommand(commandName);
                    command.execute(context, parts);
                } catch (CalculatorException e) {
                    System.out.println("Cannot to execute command: " + e.getMessage());
                    continue;
                }
            }
        }
        catch(CalculatorException e){
            System.out.println(e.getMessage());
        }
    }
}
