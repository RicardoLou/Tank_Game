package com.Ricardo;

import javax.swing.*;
import java.awt.*;

public class TankTest extends JFrame {
     public TankTest() {
         setTitle("坦克大战");
         setSize(600, 500);
         // 居中
         setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Container container = getContentPane();
        TankPanel p = new TankPanel();
        container.add(p);
        p.addMouseListener(p);

        setVisible(true);
     }


    public static void main(String[] args) {
        new TankTest();

    }
}
