package com.Ricardo.view;

import javax.swing.*;
import java.awt.*;

public class LoseView extends JFrame {
    public LoseView() {
        setTitle("You are lose");
        setSize(550, 380);
        // 居中
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.black);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GREEN);
        g.setFont(new Font("楷体", Font.BOLD, 28));
        g.drawString("You are die", 200, 200);
    }
}
