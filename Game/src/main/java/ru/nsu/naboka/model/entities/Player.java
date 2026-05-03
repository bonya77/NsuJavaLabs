package ru.nsu.naboka.model.entities;

import ru.nsu.naboka.controller.Direction;

public class Player extends Entity {

    private int gold_amount;
    private int gold_capacity;

    private Inventory inventory;
    private final int speed = 1;

    public Player(int startX, int startY){
        x_position = startX;
        y_position = startY;
    }
    public void move(Direction direction) {
        switch(direction){
            case UP -> y_position -= speed;
            case DOWN -> y_position += speed;
            case LEFT -> x_position -=speed;
            case RIGHT -> x_position += speed;
        }
    }

    public void interract(){

    }





}
