package ru.nsu.naboka.model;

import ru.nsu.naboka.controller.Direction;
import ru.nsu.naboka.model.entities.Entity;
import ru.nsu.naboka.model.entities.Machine;
import ru.nsu.naboka.model.entities.Player;
import ru.nsu.naboka.model.map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    private final Player player;
    private ArrayList<Machine> machines;
    private GameMap map;
    private ArrayList<GameObserver> subscriders = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();
    public GameWorld() {
        this.player = new Player(0, 0);
    }

    public void subscribe(GameObserver gameObserver){
        subscriders.add(gameObserver);
    }

    public void notifySubscribers(){
        for(GameObserver obs : subscriders){
            obs.OnModelUpdated();
        }
    }

    public void interract(){
        for(Entity ent : entities){
            if(ent != player && player.entitiesNear(ent)){
                ent.onInteract(player);
            }
        }
    }

    public void update(){
        for(Entity ent : entities){
            ent.update();
        }
        player.update();
    }

    public Player getPlayer(){
        return player;
    }
}
