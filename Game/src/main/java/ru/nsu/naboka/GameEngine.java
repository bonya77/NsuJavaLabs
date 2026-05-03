package ru.nsu.naboka;

import ru.nsu.naboka.controller.Controller;
import ru.nsu.naboka.model.GameWorld;

import javax.swing.*;

public class GameEngine {

    private GameWorld model;
    private Controller controller;
    private Timer timer;

    public GameEngine(GameWorld model, Controller controller){
        this.model = model;
        this.controller = controller;
        initTimer();
    }

    private void initTimer(){
        this.timer = new Timer(16, e -> {
            controller.updateInput();

            model.update();

            model.notifySubscribers();
        });
    }

    void start(){
        timer.start();
    }

    void stop(){
        timer.stop();
    }

}
