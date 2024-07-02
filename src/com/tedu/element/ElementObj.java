package com.tedu.element;

import javax.swing.*;
import java.awt.*;

/**
 * @author renjj
 * @˵�� ����Ԫ�صĻ��ࡣ
 */
public abstract class ElementObj {

    private int x;
    private int y;
    private int w;
    private int h;
    private ImageIcon icon;
    //	���С������� ���ֱ�Ҫ��״ֵ̬�����磺�Ƿ�����.
    private boolean live = true; //����״̬ true ������ڣ�false��������

    // ���Բ���ö��ֵ���������(���棬�����������޵�)
//	ע���������¶���һ�������ж�״̬�ı�������Ҫ˼����1.��ʼ�� 2.ֵ�ĸı� 3.ֵ���ж�
    public ElementObj() {    //���������ʵû�����ã�ֻ��Ϊ�̳е�ʱ�򲻱���д��
    }

    /**
     * @param x    ���Ͻ�X����
     * @param y    ���Ͻ�y����
     * @param w    w���
     * @param h    h�߶�
     * @param icon ͼƬ
     * @˵�� �������Ĺ��췽��; ���������ഫ�����ݵ�����
     */
    public ElementObj(int x, int y, int w, int h, ImageIcon icon) {
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.icon = icon;
    }

    /**
     * @param g ���� ���ڽ��л滭
     * @˵�� ���󷽷�����ʾԪ��
     */
    public abstract void showElement(Graphics g);

    /**
     * @param bl  ���������  true�����£�false�����ɿ�
     * @param key �������ļ��̵�codeֵ
     * @˵�� ʹ�ø��ඨ����ռ����¼��ķ���
     * ֻ����Ҫʵ�ּ��̼��������࣬��д�������(Լ��)
     * @˵�� ��ʽ2 ʹ�ýӿڵķ�ʽ;ʹ�ýӿڷ�ʽ��Ҫ�ڼ������������ת��
     * @���⻰ Լ��  ����  ���ڴ󲿷ֵ�java��ܶ�����Ҫ�������õ�.
     * Լ����������
     * @��չ �������Ƿ���Է�Ϊ2��������1�����հ��£�1�������ɿ�(��ͬѧ��չʹ��)
     */
    public void keyClick(boolean bl, int key) {  //�����������ǿ�Ʊ�����д�ġ�
        System.out.println("����ʹ��");
    }

    /**
     * @˵�� �ƶ�����; ��Ҫ�ƶ������࣬�� ��д�������
     */
    protected void move() {
    }

    public void stop(boolean flag) {
    }

    /**
     * @���ģʽ ģ��ģʽ;��ģ��ģʽ�ж��� ����ִ�з������Ⱥ�˳��,������ѡ������д����
     * 1.�ƶ�  2.��װ  3.�ӵ�����
     */
    public final void model(long gameTime) {
//		�Ȼ�װ
        updateImage();
//		���ƶ�
        move();
//		�ڷ����ӵ�
        add(gameTime);
    }

    //	 long ... aaa  �������� ����,����������������� N�� long���͵�����
    protected void updateImage() {
    }

    protected void add(long gameTime) {
    }

    //	��������  ������̳е�
    public void die() {  //����Ҳ��һ������

    }

    public void changeWay() {
    }

    public void addNum() {
    }

    public ElementObj createElement(String str) {

        return null;
    }

    /**
     * @return
     * @˵�� ���������� Ԫ�ص���ײ���ζ���(ʵʱ����)
     */
    public Rectangle getRectangle() {
//		���Խ�������ݽ��д��� 
        return new Rectangle(x, y, w, h);
    }

    /**
     * @param obj
     * @return boolean ����true ˵������ײ������false˵��û����ײ
     * @˵�� ��ײ����
     * һ���� this���� һ���Ǵ���ֵ obj
     */
    public boolean pk(ElementObj obj) {
        return this.getRectangle().intersects(obj.getRectangle());
    }


    /**
     * ֻҪ�� VO�� POJO ��ҪΪ�������� get��set����
     */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }


}










