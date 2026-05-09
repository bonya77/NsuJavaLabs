package ru.nsu.naboka.model;

import ru.nsu.naboka.model.entities.*;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    private final Player player;
    private ArrayList<AbstractMachine> machines;

    private ArrayList<GameObserver> subscriders = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();
    public GameWorld() {
        loadForestLevel();
        this.player = new Player(0, 0);
    }


    public void subscribe(GameObserver gameObserver){
        subscriders.add(gameObserver);
    }

    public void loadForestLevel() {
        entities.clear();
        // Генерируем 10 случайных деревьев
        for (int i = 0; i < 10; i++) {
            int randomX = (int) (Math.random() * 800);
            int randomY = (int) (Math.random() * 600);
            entities.add(new Tree(randomX, randomY));
        }
    }

    public List<Entity> getEntities(){
        return entities;
    }

    public void notifySubscribers(){
        for(GameObserver obs : subscriders){
            obs.OnModelUpdated();
        }
    }

    public void addEntity(Entity ent){
        entities.add(ent);
    }

    public void interact(){
        int necessaryDistance = 50; //в пикселях
        for(Entity ent : entities){
            if(ent instanceof Interactable && ent != player){
                double distSq = ent.entitiesDistance(player);

                if(distSq <= necessaryDistance*necessaryDistance){
                    ((Interactable) ent).interact(player);
                    break;
                }
            }
        }
    }


    public void update(){
        for(Entity ent : entities){
            ent.update();
        }
        player.update();
        if(player.isInteract()){
            interact();
        }
    }

    public Player getPlayer(){
        return player;
    }
}
