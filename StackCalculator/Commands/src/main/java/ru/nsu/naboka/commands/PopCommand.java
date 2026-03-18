package ru.nsu.naboka.commands;

import ru.nsu.naboka.Context;
import ru.nsu.naboka.exceptions.CommandException;

import java.util.List;

public class PopCommand implements ru.nsu.naboka.Command {
    public void execute(Context context, List<String> args){
        if(context.getStack().isEmpty()){
            throw new CommandException("not enough arguments in the stack to perform the operation");
        }
        context.getStack().pop();
    }
}
