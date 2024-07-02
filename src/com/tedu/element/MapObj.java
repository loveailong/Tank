package com.tedu.element;

import javax.swing.*;
import java.awt.*;

public class MapObj extends ElementObj {
    //	ǽ��ҪѪ��
    private int hp;
    private String name;//ǽ��type  Ҳ����ʹ��ö��

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(),
                this.getX(), this.getY(),
                this.getW(), this.getH(), null);
    }

    @Override   // ������Դ���   ǽ����,x,y
    public ElementObj createElement(String str) {
//		System.out.println(str); // ����,x,y
        //ȷ�����ݴ����ǶԵġ� ��ΪֻҪ��ʹ�õ������ļ�����ô�����ļ��ĸ�ʽһ��Ҫ��ȷ��
//		ֻҪ����Ҫ���ַ�����������ôһ��Ҫ��֤�ַ����ĸ�ʽ�Ƿ���Ҫ���
        String[] arr = str.split(",");
//		��дһ����ͼƬ��˵
        ImageIcon icon = null;
        switch (arr[0]) { //����ͼƬ��Ϣ ͼƬ��δ���ص��ڴ���
            case "GRASS":
                icon = new ImageIcon("image/wall/grass.png");
                break;
            case "BRICK":
                icon = new ImageIcon("image/wall/brick.png");
                break;
            case "RIVER":
                icon = new ImageIcon("image/wall/river.png");
                break;
            case "IRON":
                icon = new ImageIcon("image/wall/iron.png");
                this.hp = 4;
                name = "IRON";
                break;
        }
        int x = Integer.parseInt(arr[1]);
        int y = Integer.parseInt(arr[2]);
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        this.setH(h);
        this.setW(w);
        this.setX(x);
        this.setY(y);
        this.setIcon(icon);
        return this;
    }

    @Override  //˵�� ������ÿ�Ѫ�ȵķ��� ��Ҫ�Լ�˼�����±�д��
    public void setLive(boolean live) {
//			������һ�� �ͼ���һ��Ѫ��
        if ("IRON".equals(name)) {// ˮ��ǽ��Ҫ4��
            this.hp--;
            if (this.hp > 0) {
                return;
            }
        }
        super.setLive(live);
    }


}




