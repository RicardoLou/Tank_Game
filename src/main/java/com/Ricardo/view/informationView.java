package com.Ricardo.view;

import com.Ricardo.DAO.informationDAO;
import com.Ricardo.bean.user;
import com.Ricardo.loader.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;


public class informationView extends JDialog{
    private JPanel jPanel = new JPanel();
    JLabel copyrightView = new JLabel("Copyright 2024 Ricardo");
    private String username;
    public informationView(JFrame owner, String userName) {
        super(owner, "个人信息", true);
        this.username = userName;
        setTitle("个人信息");
        setSize(600, 430);
        // 居中
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        //设置icon
        this.setIconImage(ResourceLoader.getInstance().getIcon());
        setBackground(Color.BLACK);
        Container contentPane = getContentPane();
        jPanel.setLayout(null);

        Font smallFont = new Font("楷体", Font.PLAIN, 12);
        copyrightView.setFont(smallFont);
        copyrightView.setBounds(240, 365, 400, 30);
        jPanel.add(copyrightView);
        contentPane.add(jPanel);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage bufferedImage = ResourceLoader.getInstance().getInformationImage();
        g.drawImage(bufferedImage, 30, 40, 100, 100, this);
        // 获取到当前登录的对象
        user u = new user();
        u = informationDAO.selectByUserName(username);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("用户ID：" + u.getUserId(), 150, 80);
        g.drawString("用户姓名：" + u.getUserName(), 150, 120);
        g.drawString("天梯Ranting:" + informationDAO.selectScoreByID(u.getUserId()), 150, 160);
    }
}