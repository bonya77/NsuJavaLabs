package ru.nsu.naboka.model.entities;

public class Stone extends ResourceSource implements Interactable{

    public Stone(int x, int y) {
        super(x, y, 20, 20);
        isAvailableForMine = true;
        this.basicType = BasicResourceType.STONE;
    }


    @Override
    public void interact(Player player) {
        if(isAvailableForMine){
            player.getInventory().addItem(basicType, 1);
            isAvailableForMine = false;
            tickCounter = 0;
        }
    }
}
