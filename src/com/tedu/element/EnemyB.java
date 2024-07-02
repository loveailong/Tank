package com.tedu.element;

import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class EnemyB extends ElementObj {

    private boolean up = false;
    private boolean down = true;
    private String fx = "up";

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
        this.setIcon(new ImageIcon("image/tank/play1/play1_up.png"));
        return this;
    }

    public void move() {
        if (this.up && this.getY() > 0) {
            this.fx = "bot2_up";
            this.setY(this.getY() - 1);
        }
        if (this.down && this.getY() < 550 - this.getW()) {
            this.fx = "bot2_down";
            this.setY(this.getY() + 1);
        }
        if (this.down && this.getY() >= 550 - this.getW()) {
            this.changeWay();
        }
        if (this.up && this.getY() <= 0) {
            this.changeWay();
        }
    }

    @Override
    public void changeWay() {
        if (this.up) {
            this.up = false;
            this.down = true;
        } else {
            this.up = true;
            this.down = false;
        }
    }

    protected void updateImage() {
        this.setIcon(GameLoad.imgMap.get(fx));
    }

}
