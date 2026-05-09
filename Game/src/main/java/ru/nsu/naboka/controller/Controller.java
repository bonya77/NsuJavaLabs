package ru.nsu.naboka.controller;

import ru.nsu.naboka.model.GameWorld;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private final GameWorld model;



    public Controller(GameWorld model){
        this.model = model;
    }

    public void keyPressed(KeyEvent event){
        updateKeyState(event.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent event){
        updateKeyState(event.getKeyCode(), false);
    }

    void updateKeyState(int keyCode, boolean state){
        switch(keyCode){
            case KeyEvent.VK_W -> model.getPlayer().setMovingUp(state);
            case KeyEvent.VK_S -> model.getPlayer().setMovingDown(state);
            case KeyEvent.VK_A -> model.getPlayer().setMovingLeft(state);
            case KeyEvent.VK_D -> model.getPlayer().setMovingRight(state);
            case KeyEvent.VK_SPACE -> model.getPlayer().setInteract(state);
        }
    }
}
