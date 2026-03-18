package ru.nsu.naboka.commands;

import ru.nsu.naboka.Context;
import ru.nsu.naboka.exceptions.CalculatorException;
import ru.nsu.naboka.exceptions.CommandException;
import ru.nsu.naboka.exceptions.UnknownCommandException;

import java.util.List;

public class AddCommand implements ru.nsu.naboka.Command {
    public void execute(Context context, List<String> args) throws CalculatorException {
        if(args.size() != 1){
            throw new UnknownCommandException("invalid number of arguments");
        }
        if(context.getStack().size() < 2){
            throw  new CommandException("not enough arguments in the stack to perform the operation");
        }
        double firstArg = context.getStack().pop();
        double secondArg = context.getStack().pop();
        context.getStack().push(firstArg + secondArg);

    }

}
