package ru.nsu.naboka.model.entities;

public enum SpecialResourceType implements ItemType {
    PLANK, IRON_INGOT;
    @Override public String getName() { return name(); }
}
