package com.Ricardo.handler;

import com.Ricardo.config.GameConfig;
import com.Ricardo.config.LevelConfig;
import com.Ricardo.constants.GameConstant;
import com.Ricardo.util.FileUtil;
import com.Ricardo.view.mainView;
import com.Ricardo.view.welcomeView;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class welcomeViewHandler implements ActionListener {
    private welcomeView view;
    public static int i;
    public String username;
    public welcomeViewHandler(welcomeView view, String username) {
        this.view = view;
        this.username = username;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String gameState = FileUtil.read(GameConfig.getInstance().getGameStateCfgFilePath());
        if (gameState != null) {
            //说明有保存
            i = JOptionPane.showConfirmDialog(view, "是否继续上次游戏", "确认游戏", JOptionPane.YES_NO_OPTION);
            if (i == 0){
                System.out.println("是");
            } else if(i == 1){
                System.out.println("我选择了否");
                cfgLevel();
            }
        } else {
            System.out.println("没有保存过");
            cfgLevel();
        }
        //清空当前界面
        view.dispose();
        new mainView(username);
    }

    private void cfgLevel() {
        String selectedLevel = "";
        if(view.getEasyBtn().isSelected()) {
            selectedLevel = GameConstant.EASY;
        } else if (view.getMiddleBtn().isSelected()) {
            selectedLevel = GameConstant.MIDDLE;
        } else if(view.getDifficultBtn().isSelected()) {
            selectedLevel = GameConstant.DIFFICULT;
        }
        // 写入文件
        String levelCfgPath = LevelConfig.getInstance().getLevelCfgPath();
        if (i == 1) {
            FileUtil.write(levelCfgPath, selectedLevel);
            LevelConfig.getInstance().setLevel(selectedLevel);
            // 使用本次选择的难度
        }
    }
}
