package ru.nsu.naboka;


import ru.nsu.naboka.exceptions.FabricException;

public class CommandFactory {
    public Command CreateCommand(String key){
        if(key.equalsIgnoreCase("define")){
            return (new DefineCommand());
        }
        else if(key.equalsIgnoreCase("add")){
            return (new AddCommand());
        }
        else if(key.equalsIgnoreCase("div")){
            return (new DivCommand());
        } else if (key.equalsIgnoreCase("print")) {
            return (new PrintCommand());
        } else if (key.equalsIgnoreCase("mul")) {
            return (new MulCommand());
        } else if (key.equalsIgnoreCase("pop")) {
            return (new PopCommand());
        } else if (key.equalsIgnoreCase("Push")) {
            return (new PushCommand());
        } else if (key.equalsIgnoreCase("sqrt")) {
            return (new SqrtCommand());
        } else if (key.equalsIgnoreCase("sub")) {
            return (new SubCommand());
        }
        throw new FabricException("UnknownCommand");
    }
}
