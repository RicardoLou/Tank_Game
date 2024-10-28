package com.Ricardo;

import com.Ricardo.config.GameConfig;
import com.Ricardo.constants.GameConstant;
import com.Ricardo.util.FileUtil;
import com.Ricardo.view.LoginView;
import com.Ricardo.view.welcomeView;

import javax.swing.*;
import java.io.File;

/**
 * @author Ricardo
 * @see <a href="www.forwave.online">浪前全新版本之坦克大战</a>
 */
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
    public App(String username) {
        String userHome = System.getProperty("user.home");
        String configDir = userHome + File.separator + "TankGame" + File.separator;
        FileUtil.createDirIfNecessary(configDir);
        GameConstant.cfgDir = configDir;

        // 保存上次游戏进度
        String gameStateFilePath = configDir + "game-state.txt";
        FileUtil.createFileIfNecessary(gameStateFilePath);
        GameConfig.getInstance().setGameStateCfgFilePath(gameStateFilePath);
        new welcomeView(username);
    }
}