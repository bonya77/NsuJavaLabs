package ru.nsu.naboka.model.entities;

public abstract class ResourceSource extends Entity{

    protected int currentAmount;
    protected int capacity;

    protected int tickCounter = 0;
    boolean isAvailableForMine; //если true - можно добывать и ресуется дерево, елси false добывать нельзя и рисуется пенек

    protected int recoveryTime = 300; //5 секунд примерно при 60 фпс будет

    BasicResourceType basicType;
    SpecialResourceType specialType;

    public ResourceSource(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public BasicResourceType getBasicType(){
        return basicType;
    }
    public SpecialResourceType getSpecialType(){
        return specialType;
    }
    public int getCurrentAmount(){
        return currentAmount;
    }



    public boolean isAvailableForMine(){
        return isAvailableForMine;
    }

    @Override
    public void update(){
        if(!isAvailableForMine){
            tickCounter++;
            if(tickCounter >= recoveryTime){
                isAvailableForMine = true;
                tickCounter = 0;
            }
        }
    }
}
