package com.dragonball.blox;

import java.awt.*;

public class World {

    public void draw(Graphics2D g2) {

        // Fondo tipo isla
        g2.setColor(new Color(40, 200, 80));
        g2.fillRect(0, 0, 2000, 2000);

        // Caminos
        g2.setColor(new Color(190, 160, 120));
        g2.fillRect(200, 300, 600, 80);

        // Zona spawn
        g2.setColor(new Color(100, 100, 255));
        g2.fillRect(50, 50, 120, 120);
    }
}