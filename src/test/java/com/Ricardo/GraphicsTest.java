package com.Ricardo;

import javax.swing.*;
import java.awt.*;

public class GraphicsTest extends JFrame {
     public GraphicsTest() {
         setTitle("坦克大战");
         setSize(600, 500);
         // 居中
         setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Container container = getContentPane();
        MyPanel p = new MyPanel();
        container.add(p);
        p.addMouseListener(p);

        setVisible(true);
     }


    public static void main(String[] args) {
        new GraphicsTest();

    }
}
