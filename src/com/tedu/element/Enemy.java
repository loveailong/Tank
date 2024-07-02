package com.tedu.element;

import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy extends ElementObj {
    private boolean left = false;
    private boolean right = true;
    private String fx = "right";

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(),
                this.getX(), this.getY(),
                this.getW(), this.getH(), null);
    }

    @Override
    public ElementObj createElement(String str) {
        Random ran = new Random();
        int x = ran.nextInt(800);
        int y = ran.nextInt(500);
        this.setX(x);
        this.setY(y);
        this.setW(35);
        this.setH(35);
        this.setIcon(new ImageIcon("image/tank/bot/bot_up.png"));
        return this;
    }

    public void move() {
        if (this.left && this.getX() > 0) {
            this.fx = "bot_left";
            this.setX(this.getX() - 1);
        }
        if (this.right && this.getX() < 765 - this.getW()) {
            this.fx = "bot_right";
            this.setX(this.getX() + 1);
        }
        if (this.right && this.getX() >= 765 - this.getW()) {
            this.changeWay();
        }
        if (this.left && this.getX() <= 0) {
            this.changeWay();
        }
    }

    @Override
    public void changeWay() {
        if (this.left) {
            this.left = false;
            this.right = true;
        } else {
            this.left = true;
            this.right = false;
        }
    }

    protected void updateImage() {
        this.setIcon(GameLoad.imgMap.get(fx));
    }

}
