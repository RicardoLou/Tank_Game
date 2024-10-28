package com.Ricardo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class MyPanel extends JPanel implements MouseListener {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(100, 100, 100, 80);
        g.drawOval(50, 100, 150, 150);
        g.drawString("Hello", 200, 300);
        URL url = GraphicsTest.class.getClassLoader().getResource("images/ED.gif");
        Image image = Toolkit.getDefaultToolkit().getImage(url);
        g.drawImage(image, 300, 300, 88, 98, this);
        g.setColor(Color.red);
        g.fill3DRect(350, 200, 100, 150, true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() + " " + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
