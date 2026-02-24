package com.dragonball.blox;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    Thread gameThread;

    KeyHandler key = new KeyHandler();

    Player player;
    Enemy enemy;
    Boss boss;
    World world;

    ArrayList<KiBlast> blasts = new ArrayList<>();

    final int tileSize = 48;
    final int screenWidth = tileSize * 16;
    final int screenHeight = tileSize * 12;

    int camX, camY;

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.black);
        setDoubleBuffered(true);

        addKeyListener(key);
        setFocusable(true);

        player = new Player(this, key);
        enemy = new Enemy(600, 300);
        boss = new Boss(1200, 600);
        world = new World();
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (true) {
            update();
            repaint();

            try {
                Thread.sleep(16);
            } catch (Exception e) {}
        }
    }

    public void update() {

        player.update();
        enemy.update(player);
        boss.update(player);

        if (key.attack) {
            if (blasts.size() < 10) {
                blasts.add(new KiBlast(player.x + 40, player.y + 20));
            }
        }

        for (int i = 0; i < blasts.size(); i++) {
            KiBlast k = blasts.get(i);
            k.update();

            if (!k.active) {
                blasts.remove(i);
                i--;
                continue;
            }

            if (k.getHitBox().intersects(enemy.getHitBox())) {
                enemy.life -= 10;
                k.active = false;
                player.gainExp(20);
            }

            if (k.getHitBox().intersects(boss.getHitBox())) {
                boss.life -= 10;
                k.active = false;
                player.gainExp(50);
            }
        }

        camX = player.x - screenWidth / 2;
        camY = player.y - screenHeight / 2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.translate(-camX, -camY);

        world.draw(g2);
        player.draw(g2);
        enemy.draw(g2);
        boss.draw(g2);

        for (KiBlast k : blasts) {
            k.draw(g2);
        }

        g2.dispose();
    }
}