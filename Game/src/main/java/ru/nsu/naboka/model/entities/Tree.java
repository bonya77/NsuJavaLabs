package ru.nsu.naboka.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Tree extends ResourceSource implements Interactable{

    public Tree(int x, int y){
        super(x, y, 64, 64);
        isAvailableForMine = true;
        this.basicType = BasicResourceType.WOOD;
    }

    @Override
    public void interact(Player player) {
        if(isAvailableForMine){
            player.getInventory().addItem(basicType,1);

            isAvailableForMine = false;
            tickCounter = 0;
        }
    }



}
