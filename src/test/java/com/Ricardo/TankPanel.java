package com.Ricardo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class TankPanel extends JPanel implements MouseListener {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Color orginColor = g.getColor();
        g.setColor(Color.yellow);
        g.fill3DRect(50, 50, 20, 90, true);
        g.fill3DRect(120, 50, 20, 90, true);
        g.setColor(orginColor);
        g.fill3DRect(70, 60, 50, 70, true);
        g.drawLine(95, 30, 95, 90);

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
