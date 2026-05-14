package ru.nsu.naboka.model.entities;

import ru.nsu.naboka.controller.Direction;

public class Player extends Entity {

    private int gold_amount;
    private int gold_capacity;

    private int currentFrame = 0;
    private int frameTimer = 0;

    private int baseSize = 64;


    private boolean movingUp, movingDown, movingLeft, movingRight, isInteract;
    private final int speed = 1;


    private final Inventory inventory = new Inventory();


    public double getScale(){
        return scale;
    }
    public boolean isMoving(){
        return (movingUp || movingDown || movingRight || movingLeft);
    }
    public boolean isMovingDown() {
        return movingDown;
    }
    public boolean isMovingLeft() {
        return movingLeft;
    }
    public boolean isMovingRight() {
        return movingRight;
    }
    public boolean isMovingUp() {
        return movingUp;
    }
    public boolean isInteract(){
        return isInteract;
    }
    public Inventory getInventory(){
        return inventory;
    }

    public void setInteract(boolean state){ this.isInteract = state; }
    public void setMovingUp(boolean state){ this.movingUp = state; }
    public void setMovingDown(boolean state){ this.movingDown = state; }
    public void setMovingLeft(boolean state){ this.movingLeft = state; }
    public void setMovingRight(boolean state){ this.movingRight = state; }

    public Player(int startX, int startY){
        super(startX, startY, (int) (64 * 3.0), (int)(64*3.0));
        this.scale = 3;
        inventory.addItem(BasicResourceType.WOOD, 1);
        inventory.addItem(BasicResourceType.STONE, 20);
    }

    private void move(Direction direction) {
        switch(direction){
            case UP -> y_position -= speed;
            case DOWN -> y_position += speed;
            case LEFT -> x_position -=speed;
            case RIGHT -> x_position += speed;
        }
    }

    @Override
    public void update(){
        if(isMovingUp()) move(Direction.UP);
        if(isMovingDown()) move(Direction.DOWN);
        if(isMovingLeft()) move(Direction.LEFT);
        if(isMovingRight()) move(Direction.RIGHT);
    }

    public void updateAnimation() {
        if (isMoving()) {
            frameTimer++;
            if (frameTimer > 10) {
                currentFrame = (currentFrame + 1) % 3;
                frameTimer = 0;
            }
        } else {
            currentFrame = 0;
        }
    }
    public int getCurrentFrame() {
        updateAnimation();
        return currentFrame;
    }

    public int getBaseSize() {
        return baseSize;
    }

    public void setBaseSize(int baseSize) {
        this.baseSize = baseSize;
    }

}
