package ru.nsu.naboka.commands;

import ru.nsu.naboka.Context;
import ru.nsu.naboka.exceptions.CalculatorException;
import ru.nsu.naboka.exceptions.UnknownCommandException;

public class DefineCommand implements Command{
    private static boolean isDigit(String str)  throws CalculatorException{
        try{
            Double.parseDouble(str);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public void execute(Context context, String[] args){
        if(args.length != 3){
            throw new UnknownCommandException("invalid number of arguments");
        }
        if(isDigit(args[1]) || !isDigit(args[2])){
            throw new CalculatorException("not right order of params");
        }
        context.getMap().put(args[1], Double.parseDouble(args[2]));
    }
}
