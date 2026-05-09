package ru.nsu.naboka;
import ru.nsu.naboka.exceptions.CalculatorException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class DecoratorCommand implements Command{

    private final Command decoratedCommand;
    private static final String LOG_FILE = "calculator.log";

    public DecoratorCommand(Command decoratedCommand) {
        this.decoratedCommand = decoratedCommand;
    }


    private String captureState(Context context) {
        StringBuilder state = new StringBuilder();
        state.append("Stack: ");

        Stack<Double> stackCopy = new Stack<>();
        stackCopy.addAll(context.getStack());
        state.append(stackCopy.toString());

        state.append(", Variables: ");
        state.append(context.getMap().toString());

        return state.toString();
    }


    @Override
    public void execute(Context context, List<String> args) throws CalculatorException {
        String beforeState = captureState(context);

        String info = "Command: " + decoratedCommand.getClass().getSimpleName() +
                "Args: " + args.toString();

        decoratedCommand.execute(context, args);
        String afterState = captureState(context);

        writeToLog(beforeState, afterState, info);

    }

    private void writeToLog(String beforeState, String afterState, String info)  {
        try(FileWriter writer = new FileWriter(LOG_FILE)){
            writer.write(beforeState + '\n' + info + "\n" + afterState);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
