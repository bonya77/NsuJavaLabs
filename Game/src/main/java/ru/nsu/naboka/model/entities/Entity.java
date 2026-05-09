package ru.nsu.naboka.model.entities;
import java.lang.Math;
public class Entity {
    protected int x_position;
    protected int y_position;
    protected int width;
    protected int height;
    protected double scale = 3.0;
    public int getX(){
        return x_position;
    }

    public int getY(){
        return y_position;
    }

    public void update() { }

    public int getCenterX(){ return x_position + width/2; }
    public int getCenterY(){ return y_position + height/2; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }

    public Entity(int x, int y, int width, int height) {
        this.x_position = x;
        this.y_position = y;
        this.width = width;
        this.height = height;
    }

    public double entitiesDistance(Entity ent) {

        return (Math.pow(this.getCenterX() - ent.getCenterX(), 2) +
                Math.pow(this.getCenterY() - ent.getCenterY(), 2));
    }

    public void onInteract(Player player){ }
}
