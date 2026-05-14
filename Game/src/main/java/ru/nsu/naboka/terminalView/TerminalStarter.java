package ru.nsu.naboka.terminalView;

import ru.nsu.naboka.controller.TerminalController;
import ru.nsu.naboka.model.GameWorld;

public class TerminalStarter {

    public void start(){
        GameWorld model = new GameWorld();
        TerminalView terminalView = new TerminalView(model);

        TerminalController terminalController = new TerminalController(model.getPlayer());
        Thread inputThread = new Thread(terminalController);
        inputThread.setDaemon(true); // Чтобы поток закрылся вместе с программой
        inputThread.start();

        model.subscribe(terminalView);
        model.gameStart();
    }

}
