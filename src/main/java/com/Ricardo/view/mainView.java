package com.Ricardo.view;

import com.Ricardo.constants.GameConstant;
import com.Ricardo.handler.mainViewHandler;
import com.Ricardo.loader.ResourceLoader;
import com.Ricardo.util.ThreadPoolExecutorUtil;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class mainView extends JFrame{
    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem pauseItem;
    private JMenuItem continueItem;
    private JMenuItem saveAndExitItem;
    private JMenu otherMenu;
    private JMenuItem informationItem;
    private final mainViewHandler viewHandler;
    private FightArea fightArea;
    public MenuBar getMenuBar() {
        return super.getMenuBar();
    }
    public mainView(String username) {
        setTitle("坦克大战");
        setSize(1600, 1000);
        // 居中
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        // todo: icon    待完善  已完善
        this.setIconImage(ResourceLoader.getInstance().getIcon());
        Container contentPane = getContentPane();
        fightArea = new FightArea(this);
        ThreadPoolExecutorUtil.execute(fightArea);

        viewHandler = new mainViewHandler(this, username);
        contentPane.add(fightArea);
        this.addKeyListener(viewHandler);
        initMenu();
        setVisible(true);
        //w:1600 h:944
        GameConstant.fireAreaWidth = fightArea.getWidth();
        GameConstant.fireAreaHeight = fightArea.getHeight();
    }
    public void initMenu() {
        Font bigFont = new Font("隶书", Font.PLAIN, 20);
        Font smallFont = new Font("隶书", Font.PLAIN, 17);
        menuBar = new JMenuBar();
        gameMenu = new JMenu("游戏设置");
        gameMenu.setFont(bigFont);
        //游戏设置的菜单项
        pauseItem = new JMenuItem("暂停");
        pauseItem.setFont(smallFont);
        pauseItem.setActionCommand("pause");//点击时传给监听
        pauseItem.addActionListener(viewHandler);
        continueItem = new JMenuItem("继续");
        continueItem.setFont(smallFont);
        continueItem.setActionCommand("continue");//点击时传给监听
        continueItem.addActionListener(viewHandler);
        saveAndExitItem = new JMenuItem("保存并退出");
        saveAndExitItem.setFont(smallFont);
        saveAndExitItem.setActionCommand("saveAndExit");//点击时传给监听
        saveAndExitItem.addActionListener(viewHandler);
        // 打包进menu
        gameMenu.add(pauseItem);
        gameMenu.add(continueItem);
        gameMenu.add(saveAndExitItem);
        menuBar.add(gameMenu);

        otherMenu = new JMenu("其他");
        otherMenu.setFont(bigFont);
        informationItem = new JMenuItem("我的信息");
        informationItem.setFont(smallFont);
        informationItem.setActionCommand("information");
        informationItem.addActionListener(viewHandler);

        otherMenu.add(informationItem);
        menuBar.add(otherMenu);

        this.setJMenuBar(menuBar);
    }

}
