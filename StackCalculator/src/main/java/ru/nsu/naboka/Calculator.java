package ru.nsu.naboka;

import ru.nsu.naboka.commands.Command;
import ru.nsu.naboka.commands.CommandFabric;
import ru.nsu.naboka.exceptions.CalculatorException;


public class Calculator {
    private final Context context = new Context();
    private final CommandFabric fabric = new CommandFabric();

    void execute(Reader reader) {
        try {
            while (reader.readNextLine()) {
                String[] parts = reader.getParts();
                if (parts[0].startsWith("#") || parts[0].isEmpty()) {
                    continue;
                }
                String commandName = parts[0];
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
