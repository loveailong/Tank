package com.tedu.element;

import java.awt.*;

public class Score extends ElementObj {
    private int num = 0;

    public void addNum() {
        this.num = num + 1;
    }

    public Score() {
    }

    @Override
    public void showElement(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(Color.black);
        Font myFont = new Font("黑体", Font.BOLD, 20);
        g.setFont(myFont);
        g.drawString("本关得分：" + String.valueOf(this.num), 30, 30);
    }

}
