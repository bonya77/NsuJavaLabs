package ru.nsu.naboka.commands;

import ru.nsu.naboka.Context;
import ru.nsu.naboka.exceptions.CalculatorException;
import ru.nsu.naboka.exceptions.UnknownCommandException;

import java.util.List;

public class DefineCommand implements ru.nsu.naboka.Command {
    private static boolean isDigit(String str)  throws CalculatorException{
        try{
            Double.parseDouble(str);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public void execute(Context context, List<String> args){
        if(args.size() != 3){
            throw new UnknownCommandException("invalid number of arguments");
        }
        if(isDigit(args.get(1)) || !isDigit(args.get(2))){
            throw new CalculatorException("not right order of params");
        }
        context.getMap().put(args.get(1), Double.parseDouble(args.get(2)));
    }
}
