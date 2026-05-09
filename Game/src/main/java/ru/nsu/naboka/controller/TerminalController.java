package ru.nsu.naboka.controller;

import ru.nsu.naboka.model.entities.Player;
import java.util.Scanner;

public class TerminalController implements Runnable {
    private final Player player;
    private boolean running = true;

    // Время в мс, за которое игрок проходит одну клетку терминала.
    // Если скорость = 1 пиксель/тик, а тик = 16мс,
    // то для шага в 20 пикселей нужно ~320мс.
    private final int STEP_DURATION_X = 160;
    private final int STEP_DURATION_Y = 240; // для шага в 30 пикселей

    public TerminalController(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine().toLowerCase();
                handleInput(input);
            }
        }
    }

    private void handleInput(String input) {
        for (char c : input.toCharArray()) {
            try {
                switch (c) {
                    case 'w' -> simulateKeyHold(Direction.UP, STEP_DURATION_Y);
                    case 's' -> simulateKeyHold(Direction.DOWN, STEP_DURATION_Y);
                    case 'a' -> simulateKeyHold(Direction.LEFT, STEP_DURATION_X);
                    case 'd' -> simulateKeyHold(Direction.RIGHT, STEP_DURATION_X);
                    case 'e' -> {
                        player.setInteract(true);

                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void simulateKeyHold(Direction dir, int duration) throws InterruptedException {
        setMoving(dir, true);

        Thread.sleep(duration);

        setMoving(dir, false);
    }

    private void setMoving(Direction dir, boolean state) {
        switch (dir) {
            case UP -> player.setMovingUp(state);
            case DOWN -> player.setMovingDown(state);
            case LEFT -> player.setMovingLeft(state);
            case RIGHT -> player.setMovingRight(state);
        }
    }
}