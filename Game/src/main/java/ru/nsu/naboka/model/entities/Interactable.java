package ru.nsu.naboka.model.entities;

public interface Interactable {
    public void interact(Player player);

    int getX();
    int getY();
}
