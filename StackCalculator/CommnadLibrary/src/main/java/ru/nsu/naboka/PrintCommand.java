package ru.nsu.naboka;


import ru.nsu.naboka.exceptions.CalculatorException;
import ru.nsu.naboka.exceptions.CommandException;
import ru.nsu.naboka.exceptions.UnknownCommandException;

import java.util.List;

public class PrintCommand implements Command {
    public void execute(Context context, List<String> args)  throws CalculatorException {
        if(args.size() != 1){
            throw new UnknownCommandException("invalid number of arguments");
        }
        if(context.getStack().isEmpty()){
            throw  new CommandException("not enough arguments in the stack to perform the operation");
        }
        System.out.println(context.getStack().peek());
    }
}
