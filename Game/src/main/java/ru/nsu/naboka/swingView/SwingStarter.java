package ru.nsu.naboka.swingView;

import ru.nsu.naboka.controller.Controller;
import ru.nsu.naboka.model.GameWorld;

import javax.swing.*;

public class SwingStarter {
    public void start(){
        GameWorld model = new GameWorld();
        GamePanel view = new GamePanel(model);
        Controller controller = new Controller(model);
        ResourceManager.loadresources();

        model.subscribe(view);

        JFrame frame = new JFrame("2d tycon");
        frame.add(view);

        frame.addKeyListener(controller);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        frame.setVisible(true);

        model.gameStart();
    }
}
