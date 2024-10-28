package com.Ricardo.view;

import com.Ricardo.config.LevelConfig;
import com.Ricardo.constants.GameConstant;
import com.Ricardo.handler.welcomeViewHandler;
import com.Ricardo.loader.ResourceLoader;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

@Getter
public class welcomeView extends JFrame {
    //难度选择
    JRadioButton easyBtn = new JRadioButton("easy");
    JRadioButton middleBtn = new JRadioButton("middle");
    JRadioButton difficultBtn = new JRadioButton("hard");
    ButtonGroup btnGroup = new ButtonGroup();
    JPanel levelPanel = new JPanel();
    LevelConfig level = LevelConfig.getInstance();

    //开始按钮
    JButton startBtn = new JButton("开始");
    welcomeViewHandler HandlerView;
    public welcomeView(String username) {
        setTitle("坦克大战");
        setSize(1000, 800);
        // 居中
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        HandlerView = new welcomeViewHandler(this, username);
        //根面板
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.gray);
        contentPane.setLayout(null);
        //难度初始化
        Font levelFont = new Font("宋体", Font.BOLD, 20);
        Color levelColor = Color.PINK;


        levelPanel.setBorder(BorderFactory.createTitledBorder(null, "难度选择:", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, levelFont, levelColor));
        levelPanel.setBackground(Color.gray);
        levelPanel.setBounds(340, 470, 350, 70);
        //单选框
        easyBtn.setFont(levelFont);
        easyBtn.setForeground(levelColor);
        easyBtn.setBackground(Color.gray);
        if (GameConstant.EASY.equals(level.getLevel())) {
            easyBtn.setSelected(true);
        }
        btnGroup.add(easyBtn);

        middleBtn.setFont(levelFont);
        middleBtn.setForeground(levelColor);
        middleBtn.setBackground(Color.gray);
        if(GameConstant.MIDDLE.equals(level.getLevel())) {
            middleBtn.setSelected(true);
        }
        btnGroup.add(middleBtn);

        difficultBtn.setFont(levelFont);
        difficultBtn.setForeground(levelColor);
        difficultBtn.setBackground(Color.gray);
        if(GameConstant.DIFFICULT.equals(level.getLevel())) {
            difficultBtn.setSelected(true);
        }
        btnGroup.add(difficultBtn);

        contentPane.add(levelPanel);
        levelPanel.add(easyBtn);
        levelPanel.add(middleBtn);
        levelPanel.add(difficultBtn);
        contentPane.add(levelPanel);

        //开始按钮
        startBtn.setFont(levelFont);
        startBtn.setForeground(levelColor);
        startBtn.setBackground(Color.gray);
        startBtn.addActionListener(HandlerView);
        startBtn.setBounds(460, 660, 100, 50);
        contentPane.add(startBtn);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Color originColor = g.getColor();
        Font font = g.getFont();
        Font newFont = new Font("微软雅黑" , Font.BOLD, 60);
        g.setFont(newFont);
        g.setColor(Color.green);
        BufferedImage img = ResourceLoader.getInstance().getWelcomeImage();
        g.drawImage(img, 270, 130, 500, 330, this);
        g.drawString("坦克大战", 390, 100);
        newFont = new Font("宋体", Font.BOLD, 17);
        g.setFont(newFont);
        g.setColor(Color.BLACK);
        g.drawString("移动坦克", 270, 610);
        g.drawString("开炮！", 650, 610);

        //wasd
        URL url = welcomeView.class.getClassLoader().getResource("images/other/wasd.png");
        Image image = Toolkit.getDefaultToolkit().getImage(url);
        g.drawImage(image, 340, 570, 100, 100, this);
        //f键
        url = welcomeView.class.getClassLoader().getResource("images/other/f键.png");
        image = Toolkit.getDefaultToolkit().getImage(url);
        g.drawImage(image, 700, 585, 50, 60, this);
    }
}
