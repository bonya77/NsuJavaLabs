package ru.nsu.naboka.model.entities;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMachine extends Entity{
    protected int processing_time;
    protected int resource_price;
    protected BasicResourceType required_resource;
    protected SpecialResourceType produced_resource;

    protected int capacityBasic;
    protected int capacitySpecial;

    protected Map<BasicResourceType, Integer> basicResourceConteiner = new HashMap<>();
    protected Map<SpecialResourceType, Integer> specialResourceTypeIntegerMap = new HashMap<>();

    public AbstractMachine(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

//    void putBasicResources(int amount){
//        if(basicResourceConteiner.){
//    }

//    SpecialResourceType takeSpecialResources(){
//
//
//    }

}
