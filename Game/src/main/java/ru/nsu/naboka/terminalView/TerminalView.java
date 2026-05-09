package ru.nsu.naboka.terminalView;

import ru.nsu.naboka.model.GameObserver;
import ru.nsu.naboka.model.GameWorld;
import ru.nsu.naboka.model.entities.Entity;
import ru.nsu.naboka.model.entities.Tree;

import java.util.Arrays;

public class TerminalView implements GameObserver {
    private final GameWorld model;

    // МЕНЯЕМ МЕСТАМИ: теперь ширина 40, высота 20
    private final int fieldHeight = 40;
    private final int fieldWidth = 80;

    // Масштаб пересчитывается автоматически
    private final int scaleX = 800 / fieldWidth;  // 800 / 40 = 20
    private final int scaleY = 600 / fieldHeight; // 600 / 20 = 30

    private final char[][] grid;
    private final char[][] lastGrid;
    private String lastInventoryString = "";

    public TerminalView(GameWorld model) {
        this.model = model;
        // Массив всегда: [высота/строки][ширина/столбцы]
        this.grid = new char[fieldHeight][fieldWidth];
        this.lastGrid = new char[fieldHeight][fieldWidth];

        clearGrid(grid);
        clearGrid(lastGrid);
    }

    @Override
    public void OnModelUpdated() {
        render();
    }

    private void clearGrid(char[][] targetGrid) {
        for (char[] row : targetGrid) {
            Arrays.fill(row, '.');
        }
    }

    private void drawPlayer() {
        // Считаем центр игрока: (x + ширина/2, y + высота/2)
        int centerX = model.getPlayer().getX() + (model.getPlayer().getWidth() / 2);
        int centerY = model.getPlayer().getY() + (model.getPlayer().getHeight() / 2);

        int px = centerX / scaleX;
        int py = centerY / scaleY;

        if (isInBounds(px, py)) {
            grid[py][px] = 'P';
        }
    }

    private void drawEntities() {
        for (Entity ent : model.getEntities()) {
            int centerX = ent.getX() + (ent.getWidth() / 2);
            int centerY = ent.getY() + (ent.getHeight() / 2);

            int tx = centerX / scaleX;
            int ty = centerY / scaleY;

            if (isInBounds(tx, ty)) {
                if (ent instanceof Tree tree) {
                    double distSq = ent.entitiesDistance(model.getPlayer());
                    if (distSq <= 50 * 50) {
                        grid[ty][tx] = '!';
                    } else {
                        grid[ty][tx] = tree.isAvailableForMine() ? 'T' : 's';
                    }
                } else {
                    grid[ty][tx] = 'E';
                }
            }
        }
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < fieldWidth && y >= 0 && y < fieldHeight;
    }

    public void render() {
        clearGrid(grid);
        drawEntities();
        drawPlayer();

        String currentInv = model.getPlayer().getInventory().getResources().toString();

        if (isStateChanged(currentInv)) {
            printFrame(currentInv);
            saveCurrentState(currentInv);
        }
    }

    private boolean isStateChanged(String currentInv) {
        if (!currentInv.equals(lastInventoryString)) return true;
        for (int i = 0; i < fieldHeight; i++) {
            if (!Arrays.equals(grid[i], lastGrid[i])) return true;
        }
        return false;
    }

    private void saveCurrentState(String currentInv) {
        lastInventoryString = currentInv;
        for (int i = 0; i < fieldHeight; i++) {
            System.arraycopy(grid[i], 0, lastGrid[i], 0, fieldWidth);
        }
    }

    private void printFrame(String inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("\033[H");
        sb.append("=== 2D FACTORY TERMINAL ===\n");

        for (char[] row : grid) {
            for (char c : row) {
                sb.append(c).append(' ');
            }
            sb.append('\n');
        }

        sb.append("\nInventory: ").append(inventory);
        sb.append("\nControls: WASD (Keep GUI window focused!)");

        System.out.print(sb.toString());
    }
}