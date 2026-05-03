package ru.nsu.naboka;


import ru.nsu.naboka.exceptions.CalculatorException;
import ru.nsu.naboka.exceptions.CommandException;
import ru.nsu.naboka.exceptions.UnknownCommandException;

import java.util.List;

public class PushCommand implements Command {
    private static boolean isDigit(String str)  throws CalculatorException{
        try{
            Double.parseDouble(str);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public void execute(Context context, List<String> args) throws CalculatorException {
        if(args.size() != 2){
            throw new UnknownCommandException("invalid number of params");
        }
        if(!isDigit(args.get(1))){
            if(context.getMap().containsKey(args.get(1))){
                context.getStack().push(context.getMap().get(args.get(1)));
            }
            else{
                throw new CommandException("parameter isn't define");
            }
        }
        else {
            context.getStack().push(Double.parseDouble(args.get(1)));
        }
    }
}
