package ru.nsu.naboka.commands;

import ru.nsu.naboka.Context;
import ru.nsu.naboka.exceptions.CalculatorException;
import ru.nsu.naboka.exceptions.CommandException;

public class PopCommand implements Command{
    public void execute(Context context, String[] args){
        if(context.getStack().isEmpty()){
            throw new CommandException("not enough arguments in the stack to perform the operation");
        }
        context.getStack().pop();
    }
}
