package ru.nsu.naboka.model.entities;

public class Machine extends Entity{
    protected int processing_time;
    protected int resource_price;
    protected BasicResourceType required_resource;
    protected SpecialResourceType produced_resource;

}
