package ru.nsu.naboka;

import ru.nsu.naboka.controller.Controller;
import ru.nsu.naboka.model.GameWorld;
import ru.nsu.naboka.view.GamePanel;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        GameWorld model = new GameWorld();
        GamePanel view = new GamePanel(model);
        Controller controller = new Controller(model);

        model.subscribe(view);

        JFrame frame = new JFrame("2d tycon");
        frame.add(view);

        frame.addKeyListener(controller);


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        GameEngine engine = new GameEngine(model, controller);
        engine.start();
    }
}
