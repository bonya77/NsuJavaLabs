package ru.nsu.naboka;
import java.util.HashMap;
import java.util.Stack;
import java.util.Map;

public class Context {
    private final Stack<Double> stack = new Stack<>();
    private final Map<String, Double> map = new HashMap<>();
    public Stack<Double> getStack(){
        return stack;
    }
    public Map<String, Double> getMap(){
        return map;
    }
}
