package ru.nsu.naboka.view;

import ru.nsu.naboka.model.GameObserver;
import ru.nsu.naboka.model.GameWorld;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements GameObserver {
    private GameWorld model;

    public GamePanel(GameWorld model){
        this.model = model;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillRect(model.getPlayer().getX(), model.getPlayer().getY(), 16, 16);
    }

    @Override
    public void OnModelUpdated() {
        repaint();
    }

}
