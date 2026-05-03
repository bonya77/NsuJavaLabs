package ru.nsu.naboka.model.entities;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<ItemType, Integer> resources = new HashMap<>();
    private final int cellCapacity = 64;

    public void addItem(ItemType type, int amount){
        if(resources.containsKey(type) || resources.size() < 8){
            resources.put(type, (Math.min((resources.getOrDefault(type, 0) + amount), cellCapacity)));
        }
        else
            return;
    }

    public int takeItem(ItemType type, int amount){
        if(!resources.containsKey(type)){
            return 0;
        }
        int numOfResources = resources.get(type);
        int remain = numOfResources - amount;
        if(remain <= 0){
            resources.remove(type);
            return numOfResources;
        }
        resources.put(type, numOfResources - amount);
        return amount;
    }
}
