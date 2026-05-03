package ru.nsu.naboka.model.entities;

public enum BasicResourceType implements ItemType {
    WOOD, STONE;
    @Override public String getName() { return name(); }
}