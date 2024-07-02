package com.tedu.controller;

import com.tedu.element.ElementObj;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * @author renjj
 * @˵�� ��Ϸ�����̣߳����ڿ�����Ϸ���أ���Ϸ�ؿ�����Ϸ����ʱ�Զ���
 * ��Ϸ�ж�����Ϸ��ͼ�л� ��Դ�ͷź����¶�ȡ������
 * @�̳� ʹ�ü̳еķ�ʽʵ�ֶ��߳�(һ�㽨��ʹ�ýӿ�ʵ��)
 */
public class GameThread extends Thread {
    private ElementManager em;
    private int level = 1;
    private int Maxlevel = 2;
    private int sumScore = 0;
    private int leveltype = 5;

    public GameThread() {
        em = ElementManager.getManager();
    }

    @Override
    public void run() {//��Ϸ��run����  ���߳�
        while (level <= Maxlevel) { //��չ,���Խ�true��Ϊһ���������ڿ��ƽ���
//		��Ϸ��ʼǰ   ����������������Ϸ��Դ(������Դ)
            gameLoad();
//		��Ϸ����ʱ   ��Ϸ������
            gameRun();
//		��Ϸ��������  ��Ϸ��Դ����(������Դ)
            gameOver();
            try {
                sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * ��Ϸ�ļ���
     */
    private void gameLoad() {
        GameLoad.loadImg(); //����ͼƬ
        GameLoad.MapLoad(level);//���Ա�Ϊ ������ÿһ�����¼���  ���ص�ͼ
//		��������
        GameLoad.loadPlay();//Ҳ���Դ���������������2��
//		���ص���NPC��
        GameLoad.loadEnemy(leveltype);
//		ȫ��������ɣ���Ϸ����
    }

    /**
     * @˵�� ��Ϸ����ʱ
     * @����˵�� ��Ϸ��������Ҫ�������飺1.�Զ�����ҵ��ƶ�����ײ������
     * 2.��Ԫ�ص�����(NPC��������ֵ���)
     * 3.��ͣ�ȵȡ���������
     * ��ʵ�����ǵ��ƶ�
     */

    private void gameRun() {
        long gameTime = 0L;//��int���;Ϳ�����
        while (true) {// Ԥ����չ   true���Ա�Ϊ���������ڿ��ƹܹؿ�������
            Map<GameElement, List<ElementObj>> all = em.getGameElements();
            List<ElementObj> enemys = em.getElementsByKey(GameElement.ENEMY);
            List<ElementObj> enemyBs = em.getElementsByKey(GameElement.ENEMYB);
            List<ElementObj> files = em.getElementsByKey(GameElement.PLAYFILE);
            List<ElementObj> maps = em.getElementsByKey(GameElement.MAPS);
            List<ElementObj> plays = em.getElementsByKey(GameElement.PLAY);
            List<ElementObj> score = em.getElementsByKey(GameElement.SCORE);
            moveAndUpdate(all, gameTime);//	��ϷԪ���Զ�������

            ElementPK_score(enemys, files, score);
            EnemyPeng(enemys, maps);
            EnemyPeng(enemys, plays);
            EnemyPeng(enemys, enemyBs);


            ElementPK_score(enemyBs, files, score);
            EnemyPeng(enemyBs, maps);
            EnemyPeng(enemyBs, plays);
            EnemyPeng(enemyBs, enemys);

            ElementPK(files, maps);
            ElementPeng(plays, maps);

            if (enemys.size() == 0) {
                level++;
                leveltype += 5;
                plays.clear();
                score.clear();
                return;
            }

            gameTime++;//Ψһ��ʱ�����
            try {
                sleep(10);//Ĭ�����Ϊ 1��ˢ��100��
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    private void EnemyPeng(List<ElementObj> listA, List<ElementObj> listB) {
        // TODO Auto-generated method stub
        for (int i = 0; i < listA.size(); i++) {
            ElementObj enemy = listA.get(i);
            for (int j = 0; j < listB.size(); j++) {
                ElementObj file = listB.get(j);
                if (enemy.pk(file)) {

                    enemy.changeWay();

                    break;
                }
            }
        }
    }

    public void ElementPK(List<ElementObj> listA, List<ElementObj> listB) {
//		����������ʹ��ѭ������һ��һ�ж������Ϊ�棬������2�����������״̬
        for (int i = 0; i < listA.size(); i++) {
            ElementObj enemy = listA.get(i);
            for (int j = 0; j < listB.size(); j++) {
                ElementObj file = listB.get(j);
                if (enemy.pk(file)) {
//					���⣺ �����boos����ôҲһǹһ���𣿣�����
//					�� setLive(false) ��Ϊһ���ܹ��������������Դ�������һ������Ĺ�����
//					���չ���������ִ��ʱ�����Ѫ����Ϊ0 �ٽ�����������Ϊ false
//					��չ �������
//					System.out.println(listB);
                    enemy.setLive(false);
                    file.setLive(false);
                    break;
                }
            }
        }
    }

    public void ElementPK_score(List<ElementObj> listA, List<ElementObj> listB, List<ElementObj> listC) {
//		����������ʹ��ѭ������һ��һ�ж������Ϊ�棬������2�����������״̬
        ElementObj score = listC.get(0);
        for (int i = 0; i < listA.size(); i++) {
            ElementObj enemy = listA.get(i);
            for (int j = 0; j < listB.size(); j++) {
                ElementObj file = listB.get(j);
                if (enemy.pk(file)) {
//					���⣺ �����boos����ôҲһǹһ���𣿣�����
//					�� setLive(false) ��Ϊһ���ܹ��������������Դ�������һ������Ĺ�����
//					���չ���������ִ��ʱ�����Ѫ����Ϊ0 �ٽ�����������Ϊ false
//					��չ �������
//					System.out.println(listB);
                    enemy.setLive(false);
                    file.setLive(false);
                    score.addNum();
                    sumScore++;

                    break;
                }
            }
        }
    }

    //�����ײ
    public void ElementPeng(List<ElementObj> listA, List<ElementObj> listB) {
        for (int i = 0; i < listA.size(); i++) {
            ElementObj enemy = listA.get(i);
            for (int j = 0; j < listB.size(); j++) {
                ElementObj file = listB.get(j);
                if (enemy.pk(file)) {
//					���⣺ �����boos����ôҲһǹһ���𣿣�����
//					�� setLive(false) ��Ϊһ���ܹ��������������Դ�������һ������Ĺ�����
//					���չ���������ִ��ʱ�����Ѫ����Ϊ0 �ٽ�����������Ϊ false
//					��չ �������
//					System.out.println(listB);
                    enemy.stop(true);
                    file.stop(true);
                    break;
                }
            }
        }
    }


    //	��ϷԪ���Զ�������
    public void moveAndUpdate(Map<GameElement, List<ElementObj>> all, long gameTime) {
//		GameElement.values();//���ط���  ����ֵ��һ������,�����˳����Ƕ���ö�ٵ�˳��
        for (GameElement ge : GameElement.values()) {
            List<ElementObj> list = all.get(ge);
//			��д����ֱ�Ӳ����������ݵĴ��뽨�鲻Ҫʹ�õ�������
//			for(int i=0;i<list.size();i++) {
            for (int i = list.size() - 1; i >= 0; i--) {
                ElementObj obj = list.get(i);//��ȡΪ����
                if (!obj.isLive()) {//�������
//					list.remove(i--);  //����ʹ�������ķ�ʽ
//					����һ����������(�����п�������������:�������� ,��װ��)
                    obj.die();//��Ҫ����Լ�����
                    list.remove(i);
                    continue;
                }
                obj.model(gameTime);//���õ�ģ�巽�� ����move
            }
        }
    }


    /**
     * ��Ϸ�л��ؿ�
     */
    private void gameOver() {
        if (level < Maxlevel) {

            JOptionPane.showMessageDialog(null, "�ܷ֣�" + sumScore + "\nȷ��5��������һ��");
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "�ܷ֣�" + sumScore);
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}





