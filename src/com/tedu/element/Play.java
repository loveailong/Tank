package com.tedu.element;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.awt.*;

public class Play extends ElementObj /* implements Comparable<Play>*/ {
    /**
     * �ƶ�����:
     * 1.������  ���  ����ö������ʹ��; һ��ֻ���ƶ�һ������
     * 2.˫����  ���� �� ����    ���booleanֵʹ�� ���磺 true������ false Ϊ�� ��������
     * ��Ҫ����һ������ȷ���Ƿ��·����
     * Լ��    0 ������   1������   2������
     * 3.4����  �������Ҷ�����  boolean���ʹ��  true �����ƶ� false ���ƶ�
     * ͬʱ���Ϻ��� ��ô�죿�󰴵Ļ������Ȱ���
     * ˵��������3�ַ�ʽ �Ǵ����д���ж���ʽ ��һ��
     * ˵������Ϸ�зǳ�����ж����������ʹ�� �ж����ԣ��ܶ�״ֵ̬Ҳʹ���ж�����
     * ��״̬ ����ʹ��map<����,boolean>;set<�ж�����> �ж���������ʱ��
     *
     * @���� 1.ͼƬҪ��ȡ���ڴ��У� ������  ��ʱ����ʽ���ֶ���д�洢���ڴ���
     * 2.ʲôʱ������޸�ͼƬ(��ΪͼƬ���ڸ����е����Դ洢)
     * 3.ͼƬӦ��ʹ��ʲô���Ͻ��д洢
     */
    private boolean left = false; //��
    private boolean up = false;   //��
    private boolean right = false;//��
    private boolean down = false; //��


    //	����ר��������¼��ǰ��������ķ���,Ĭ��Ϊ��up
    private String fx = "up";
    private boolean pkType = false;//����״̬ true ����  falseֹͣ

    public Play() {
    }

    public Play(int x, int y, int w, int h, ImageIcon icon) {
        super(x, y, w, h, icon);
    }

    //���⻰: ��ʱ�ķ��������� �����ã�Ҳ�ܹ��ã���Ϊ�㲻��jdk�ײ�ʹ��
    @Override
    public ElementObj createElement(String str) {
        String[] split = str.split(",");
        this.setX(Integer.parseInt(split[0]));
        this.setY(Integer.parseInt(split[1]));
        ImageIcon icon2 = GameLoad.imgMap.get(split[2]);
        this.setW(icon2.getIconWidth());
        this.setH(icon2.getIconHeight());
        this.setIcon(icon2);
        return this;
    }


    /**
     * ��������е�1��˼�룺 �����Լ��������Լ���
     */
    @Override
    public void showElement(Graphics g) {
//		�滭ͼƬ
        g.drawImage(this.getIcon().getImage(),
                this.getX(), this.getY(),
                this.getW(), this.getH(), null);
    }

    /*
     * @˵�� ��д������ ��д��Ҫ�󣺷������� �Ͳ����������� ����͸���ķ���һ��
     * @�ص� ������������Ҫ�ı�״ֵ̬
     */
    @Override   // ע�� ͨ��������ƣ�Ϊ����߷����������� ��ӵ�ע��(�൱�����֤�ж�)
    public void keyClick(boolean bl, int key) { //ֻ�а��»�����_�ŕ� �����������
//		System.out.println("���ԣ�"+key);
        if (bl) {//����
            switch (key) {  //��ô�Ż� �������˼��;�� �����������������û�취����һ��
                case 37:
                    this.down = false;
                    this.up = false;
                    this.right = false;
                    this.left = true;
                    this.fx = "left";
                    break;
                case 38:
                    this.right = false;
                    this.left = false;
                    this.down = false;
                    this.up = true;
                    this.fx = "up";
                    break;
                case 39:
                    this.down = false;
                    this.up = false;
                    this.left = false;
                    this.right = true;
                    this.fx = "right";
                    break;
                case 40:
                    this.right = false;
                    this.left = false;
                    this.up = false;
                    this.down = true;
                    this.fx = "down";
                    break;
                case 32:
                    this.pkType = true;
                    break;//��������״̬
            }
        } else {
            switch (key) {
                case 37:
                    this.left = false;
                    break;
                case 38:
                    this.up = false;
                    break;
                case 39:
                    this.right = false;
                    break;
                case 40:
                    this.down = false;
                    break;
                case 32:
                    this.pkType = false;
                    break;//�رչ���״̬
            }
            //a a
        }
    }


    //	@Override
//	public int compareTo(Play o) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
    @Override
    public void move() {
        if (this.left && this.getX() > 0) {
            this.setX(this.getX() - 1);
        }
        if (this.up && this.getY() > 0) {
            this.setY(this.getY() - 1);
        }
        if (this.right && this.getX() < 760 - this.getW()) {  //�������ת�ɴ�������
            this.setX(this.getX() + 1);
        }
        if (this.down && this.getY() < 530) {
            this.setY(this.getY() + 1);
        }
    }

    @Override
    protected void updateImage() {
//		ImageIcon icon=GameLoad.imgMap.get(fx);
//		System.out.println(icon.getIconHeight());//�õ�ͼƬ�ĸ߶�
//		����߶���С�ڵ���0 ��˵��������ͼƬ·��������
        this.setIcon(GameLoad.imgMap.get(fx));
    }

    /**
     * @�������⣺1.������д�ķ����ķ������η��Ƿ�����޸ģ� 2.���������add�����Ƿ�����Զ��׳��쳣?
     * @��д����1.��д�����ķ������ƺͷ���ֵ ����͸����һ��
     * 2.��д�ķ����Ĵ�������������У�����͸����һ��
     * 3.��д�ķ����������η� ֻ�� �ȸ���ĸ��ӿ���
     * �ȷ�˵������ķ������ܱ����ģ�����������Ҫ�ڷ��������
     * ����ֱ������̳У���д��super.���෽����public����
     * 4.��д�ķ����׳����쳣 �����Աȸ�����ӿ�
     * �ӵ������ ��Ҫ���� �����ߵ�����λ�ã������ߵķ���  �������Ա任�ӵ�(˼������ô����)
     */
    private long filetime = 0;

    //	filefime �ʹ����ʱ�� gameTime ���бȽϣ���ֵ�Ȳ������㣬�����ӵ����
//	������ƴ��� �Լ�д
    @Override   //����ӵ�
    public void add(long gameTime) {//����ʱ��Ϳ��Խ��п���
        if (!this.pkType) {//����ǲ�����״̬ ��ֱ��return
            return;
        }
        this.pkType = false;//��һ�Σ�����һ���ӵ���ƴ����(Ҳ�������ӱ���������)
//		new PlayFile(); // ����һ���� ��Ҫ���Ƚ϶�Ĺ���  ����ѡ��һ�ַ�ʽ��ʹ��С����
//		���������Ķ��������з�װ��Ϊһ������������ֱֵ�����������
//		����һ���̶���ʽ   {X:3,y:5,f:up} json��ʽ
        ElementObj obj = GameLoad.getObj("file");
        ElementObj element = obj.createElement(this.toString());
//		System.out.println("�ӵ��Ƿ�Ϊ��"+element);
//		װ�뵽������
        ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
//		���Ҫ�����ӵ��ٶȵȵȡ�����������Ҫ�����д
    }

    @Override
    public String toString() {// ������͵����ֱ��ʹ��toString�������Լ�����һ������
        //  {X:3,y:5,f:up,t:A} json��ʽ
        int x = this.getX();
        int y = this.getY();
        switch (this.fx) { // �ӵ��ڷ���ʱ����Ѿ�����̶��Ĺ켣�����Լ���Ŀ�꣬�޸�json��ʽ
            case "up":
                x += this.getW() / 2 - 5;
                break;
            // һ�㲻��д20����ֵ��һ������� ͼƬ��С������ʾ��С��һ������¿���ʹ��ͼƬ��С��������
            case "left":
                y += this.getH() / 2 - 5;
                break;
            case "right":
                x += this.getW() - 5;
                y += this.getH() / 2 - 5;
                break;
            case "down":
                y += this.getH() - 5;
                x += this.getW() / 2 - 5;
                break;
        }//������Ϊ�� ����Ϸ������ ����������˼��;����ר���棬��Ҫ˼��������Ӧ����ô��������Ӧ����ôʵ��
//		ѧϰ���������������ǲ�Ҫ�ü�������������.
        return "x:" + x + ",y:" + y + ",f:" + this.fx;
    }

    /*
     * @parm boolean flag �ܷ��ƶ� true���� false��
     * */
    @Override
    public void stop(boolean flag) {
        if (flag) {
            changeWay();
        }
    }

    @Override
    public void changeWay() {
        if (this.left) {
            this.setX(this.getX() + 1);
        }
        if (this.right) {
            this.setX(this.getX() - 1);
        }
        if (this.down) {
            this.setY(this.getY() - 1);
        }
        if (this.up) {
            this.setY(this.getY() + 1);
        }
    }


}

//try {
//Class<?> forName = Class.forName("com.tedu.....");
//ElementObj element = forName.newInstance().createElement("");
//} catch (InstantiationException e) {
//// TODO Auto-generated catch block
//e.printStackTrace();
//} catch (IllegalAccessException e) {
//// TODO Auto-generated catch block
//e.printStackTrace();
//} //�Ժ�Ŀ��ѧϰ�л�����
//// ������㷵�ض����ʵ�壬����ʼ������
//catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//}





