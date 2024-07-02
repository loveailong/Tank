package com.tedu.game;

import com.tedu.controller.GameListener;
import com.tedu.controller.GameThread;
import com.tedu.show.GameJFrame;
import com.tedu.show.GameMainJPanel;

public class GameStart {
    public static void main(String[] args) {
        GameJFrame gj = new GameJFrame();
        GameMainJPanel jp = new GameMainJPanel();
        GameListener listener = new GameListener();
        GameThread th = new GameThread();

        gj.setjPanel(jp);
        gj.setKeyListener(listener);
        gj.setThead(th);

        gj.start();

    }

}
