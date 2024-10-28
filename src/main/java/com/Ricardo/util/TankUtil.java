package com.Ricardo.util;

import com.Ricardo.constants.GameConstant;

import java.awt.event.KeyEvent;

public class TankUtil {
    public static String convertCode2Direction(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            return GameConstant.DIRECTION_UP;
        } else if(keyCode == KeyEvent.VK_A) {
            return GameConstant.DIRECTION_LEFT;
        } else if(keyCode == KeyEvent.VK_S) {
            return GameConstant.DIRECTION_DOWN;
        } else if(keyCode == KeyEvent.VK_D) {
            return GameConstant.DIRECTION_RIGHT;
        }
        return null;
    }

    /**
     * 随机方向
     * @param max
     * @return
     */
    public static int random(int max) {
        double random = Math.random();
        int round = (int)Math.round(random * max);
        return round;
    }
    public static int randomStep(int max) {
        double random = Math.random();
        int round = (int) Math.round(random * max);
        return round;
    }
}
