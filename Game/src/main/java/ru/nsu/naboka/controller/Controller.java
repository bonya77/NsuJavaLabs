package ru.nsu.naboka.controller;

import ru.nsu.naboka.model.GameWorld;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class Controller extends KeyAdapter {
    private final GameWorld model;

    public Controller(GameWorld model){
        this.model = model;
    }

    private final Set<Integer> pressedKeys = new HashSet<>();

    public void keyPressed(KeyEvent event){
        pressedKeys.add(event.getKeyCode());
    }

    public void keyReleased(KeyEvent event){
        pressedKeys.remove(event.getKeyCode());
    }


    public void updateInput(){
        if(pressedKeys.contains(KeyEvent.VK_W)){
            model.getPlayer().move(Direction.UP);
        }
        if(pressedKeys.contains(KeyEvent.VK_S)){
            model.getPlayer().move(Direction.DOWN);
        }
        if(pressedKeys.contains(KeyEvent.VK_A)){
            model.getPlayer().move(Direction.LEFT);
        }
        if(pressedKeys.contains(KeyEvent.VK_D)){
            model.getPlayer().move(Direction.RIGHT);
        }
        if(pressedKeys.contains(KeyEvent.VK_E)){
            model.interract();
        }
    }
}
