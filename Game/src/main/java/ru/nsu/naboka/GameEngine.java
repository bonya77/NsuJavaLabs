package ru.nsu.naboka;

import ru.nsu.naboka.controller.Controller;
import ru.nsu.naboka.model.GameWorld;
import ru.nsu.naboka.view.ResourceManager;

import javax.swing.*;

public class GameEngine {

    private final GameWorld model;
    private Timer timer;

    public GameEngine(GameWorld model, Controller controller){
        this.model = model;
        initTimer();
        ResourceManager.loadresources();
    }

    private void initTimer(){
        this.timer = new Timer(16, e -> {
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
