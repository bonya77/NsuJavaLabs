package ru.nsu.naboka.model.entities;

public class Entity {
    protected int x_position;
    protected int y_position;

    public int getX(){
        return x_position;
    }

    public int getY(){
        return y_position;
    }

    public void update() { }

    public boolean entitiesNear(Entity ent){
        return ((this.getX() - ent.getX() <= 2) && (this.getY() - ent.getY()) <= 2);
    }

    public void onInteract(Player player){ }
}
