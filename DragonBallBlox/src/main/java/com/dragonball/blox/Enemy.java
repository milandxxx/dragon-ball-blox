package com.dragonball.blox;

import java.awt.*;
import java.util.Random;

public class Enemy {

    public int x, y;
    int speed = 2;
    int life = 100;

    Random rand = new Random();

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(Player player) {
        // IA básica: seguir al jugador
        if (player.x > x) x += speed;
        if (player.x < x) x -= speed;

        if (player.y > y) y += speed;
        if (player.y < y) y -= speed;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(x, y, 40, 40);

        // Barra de vida
        g2.setColor(Color.GREEN);
        g2.fillRect(x, y - 8, life / 2, 5);
    }

    public Rectangle getHitBox() {
        return new Rectangle(x, y, 40, 40);
    }
}