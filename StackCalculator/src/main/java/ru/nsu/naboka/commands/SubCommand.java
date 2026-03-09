package ru.nsu.naboka.commands;

import ru.nsu.naboka.Context;
import ru.nsu.naboka.exceptions.CalculatorException;
import ru.nsu.naboka.exceptions.CommandException;

public class SubCommand implements Command{
    public void execute(Context context, String[] args)  throws CalculatorException{
        if(args.length != 1){
            throw new CommandException("invalid number of arguments");
        }
        if(context.getStack().size() < 2){
            throw  new CommandException("not enough arguments in the stack to perform the operation");
        }

        double firstArg = context.getStack().pop();
        double secondArg = context.getStack().pop();
        context.getStack().push(secondArg - firstArg);
    }


}
