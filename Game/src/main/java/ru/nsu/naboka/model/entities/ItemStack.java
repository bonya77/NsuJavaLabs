package ru.nsu.naboka.model.entities;

public class ItemStack {
    private int count;
    ItemType type;


    public ItemStack(int count, ItemType type){
        this.count = count;
        this.type = type;
    }

    int getCount(){ return count; }
    ItemType getType(){ return type;}

    void remove(ItemType type){
        count = 0;
    }

    public void add(int amount){
        count += amount;
    }

}
