package ru.nsu.naboka.view;

import ru.nsu.naboka.model.GameObserver;
import ru.nsu.naboka.model.GameWorld;
import ru.nsu.naboka.model.entities.Entity;
import ru.nsu.naboka.model.entities.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class GamePanel extends JPanel implements GameObserver {
    private final GameWorld model;

    public GamePanel(GameWorld model){
        this.model = model;
    }

    private void drawInventory(Graphics g){
        int x = 20;
        int y = getHeight() - 80;
        int slotSize = 50;
        int padding = 10;

        var resources = model.getPlayer().getInventory().getResources();

        for (var entry : resources.entrySet()) {
        // Рисуем рамку слота
            g.setColor(new Color(0, 0, 0, 180));
            g.fillRoundRect(x, y, slotSize, slotSize, 10, 10);
            g.setColor(Color.GRAY);
            g.drawRoundRect(x, y, slotSize, slotSize, 10, 10);

            // Пишем название ресурса и количество
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString(entry.getKey().toString(), x + 5, y + 25);
            g.setColor(Color.YELLOW);
            g.drawString("x" + entry.getValue(), x + 5, y + 45);

            x += slotSize + padding;
        }

    }

    private void drawPlayer(Graphics g){
        BufferedImage player = ResourceManager.getPlayerSprite();
        int frameWidth = player.getWidth() / 3;
        int frameHeight = player.getHeight() / 4;

        int row = 0;

        if (model.getPlayer().isMovingRight()) row = 1;
        else if (model.getPlayer().isMovingDown()) row = 0;
        else if (model.getPlayer().isMovingUp()) row = 2;
        else if (model.getPlayer().isMovingLeft()) row = 3;

        int sx1 = model.getPlayer().getCurrentFrame() * frameWidth;
        int sy1 = row * frameHeight;
        int sx2 = sx1 + frameWidth;
        int sy2 = sy1 + frameHeight;

        int playerSize = (int) ( model.getPlayer().getBaseSize() * model.getPlayer().getScale());

        g.drawImage(player,
                model.getPlayer().getX(), model.getPlayer().getY(),
                model.getPlayer().getX() + playerSize, model.getPlayer().getY() + playerSize,
                sx1, sy1, sx2, sy2,
                null);
    }

    private void drawBackground(Graphics g){
        BufferedImage grass = ResourceManager.getBackgroundTileSpriteSprite();
        int width = grass.getWidth();
        int height = grass.getHeight();
        for(int x = 0; x < getWidth(); x += width){
            for(int y = 0; y < getHeight(); y += height){
                g.drawImage(grass, x, y, null);
            }
        }
    }

    private void drawOtherEntities(Graphics g){
        for(Entity ent : model.getEntities()){
            int x = ent.getX();
            int y = ent.getY();
            if(ent instanceof Tree tree){
                BufferedImage treeSprite = tree.isAvailableForMine() ?
                        ResourceManager.getTreeSprite() :
                        ResourceManager.getStumpSprite();
                g.drawImage(treeSprite, x, y, tree.getWidth(), tree.getHeight(), null);
            }

        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //фон
        drawBackground(g);
        //остальные игровые объекты
        drawOtherEntities(g);
        //ирок
        drawPlayer(g);
        //инвентарь
        drawInventory(g);

    }


    @Override
    public void OnModelUpdated() {
        repaint();
    }

}
