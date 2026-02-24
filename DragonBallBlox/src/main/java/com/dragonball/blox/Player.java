package com.dragonball.blox;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Player {

    GamePanel gp;
    KeyHandler key;

    public int x = 200;
    public int y = 200;
    int speed = 4;

    int level = 1;
    int exp = 0;
    int expNext = 100;

    BufferedImage sprite;

    public Player(GamePanel gp, KeyHandler key) {
        this.gp = gp;
        this.key = key;
        loadSprite();
    }

    void loadSprite() {
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/goku.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (key.up) y -= speed;
        if (key.down) y += speed;
        if (key.left) x -= speed;
        if (key.right) x += speed;
    }

    public void gainExp(int value) {
        exp += value;
        if (exp >= expNext) {
            level++;
            exp = 0;
            expNext += 50;
            speed++;
            System.out.println("LEVEL UP → " + level);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(sprite, x, y, 64, 64, null);
        g2.setColor(Color.YELLOW);
        g2.drawString("LV " + level, x, y - 5);
    }

    public Rectangle getHitBox() {
        return new Rectangle(x, y, 64, 64);
    }
}