package com.dragonball.blox;

import java.awt.Rectangle;

public class CombatSystem {

    public static void attack(Player player, Enemy enemy) {

        Rectangle atkBox = new Rectangle(
                player.x - 10,
                player.y - 10,
                80,
                80
        );

        if (atkBox.intersects(enemy.getHitBox())) {
            enemy.life -= 10;
        }
    }
}