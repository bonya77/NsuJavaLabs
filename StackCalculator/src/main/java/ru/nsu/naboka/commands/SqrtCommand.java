package ru.nsu.naboka.commands;
import ru.nsu.naboka.Context;
import ru.nsu.naboka.exceptions.CalculatorException;
import ru.nsu.naboka.exceptions.CommandException;
import ru.nsu.naboka.exceptions.UnknownCommandException;

import static java.lang.Math.sqrt;

public class SqrtCommand implements Command{
    public void execute(Context context, String[] args)  throws CalculatorException{
        if(args.length != 1){
            throw new UnknownCommandException("invalid number of arguments");
        }
        if(context.getStack().isEmpty()){
            throw  new CommandException("not enough arguments in the stack to perform the operation");
        }

        context.getStack().push(sqrt(context.getStack().pop()));

    }
}
