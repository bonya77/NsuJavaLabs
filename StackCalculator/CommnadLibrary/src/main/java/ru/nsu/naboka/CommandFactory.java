package ru.nsu.naboka;


import ru.nsu.naboka.exceptions.FabricException;

import java.util.List;

public class CommandFactory {
    public Command CreateCommand(String key, List<String> args){
        boolean needToDecorate = false;
        if(key.equalsIgnoreCase("log")){

            needToDecorate = true;
            key = args.removeFirst();
        }
        Command command;
        if (key.equalsIgnoreCase("define")) {
            command = new DefineCommand();
        } else if (key.equalsIgnoreCase("add")) {
            command = new AddCommand();
        } else if (key.equalsIgnoreCase("div")) {
            command = new DivCommand();
        } else if (key.equalsIgnoreCase("print")) {
            command = new PrintCommand();
        } else if (key.equalsIgnoreCase("mul")) {
            command = new MulCommand();
        } else if (key.equalsIgnoreCase("pop")) {
            command = new PopCommand();
        } else if (key.equalsIgnoreCase("push")) {
            command = new PushCommand();
        } else if (key.equalsIgnoreCase("sqrt")) {
            command = new SqrtCommand();
        } else if (key.equalsIgnoreCase("sub")) {
            command = new SubCommand();
        } else {
            throw new FabricException("UnknownCommand: " + key);
        }

        if (needToDecorate) {
            return new DecoratorCommand(command);
        }

        return command;
    }
}
