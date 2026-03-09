package ru.nsu.naboka.commands;

import ru.nsu.naboka.Context;
import ru.nsu.naboka.exceptions.CalculatorException;
import ru.nsu.naboka.exceptions.CommandException;
import ru.nsu.naboka.exceptions.UnknownCommandException;

public class PushCommand implements Command{
    private static boolean isDigit(String str)  throws CalculatorException{
        try{
            Double.parseDouble(str);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public void execute(Context context, String[] args) throws CalculatorException{
        if(args.length != 2){
            throw new UnknownCommandException("invalid number of params");
        }
        if(!isDigit(args[1])){
            if(context.getMap().containsKey(args[1])){
                context.getStack().push(context.getMap().get(args[1]));
            }
            else{
                throw new CommandException("parameter isn't define");
            }
        }
        else {
            context.getStack().push(Double.parseDouble(args[1]));
        }
    }
}
