package com.Ricardo.config;

import com.Ricardo.bean.BulletColor;
import com.Ricardo.constants.GameConstant;
import com.Ricardo.util.FileUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter @Setter
public class LevelConfig {
    private String level;
    private int enemyCount = 5;
    private int enemyStep = 0;
    private int period = 3000;
    private int speed = 15;
    private BulletColor bulletColor = new BulletColor();
    private static LevelConfig levelConfig = new LevelConfig();
    //级别配置文件路径
    private String levelCfgPath;
    public static LevelConfig getInstance() {
        return levelConfig;
    }
    public LevelConfig() {
        //加载上次选择的游戏难度
        String levelCfgFilePath = GameConstant.cfgDir + File.separator + "tank-level.ini";
        FileUtil.createFileIfNecessary(levelCfgFilePath);
        this.setLevelCfgPath(levelCfgFilePath);
        String lastLevel = FileUtil.read(levelCfgFilePath);
        if (lastLevel != null) {
            //回显
            this.level = lastLevel;
        }
        if(GameConstant.EASY.equals(this.level)) {
            enemyCount = 3;
            enemyStep = 2;
            period = 3000;
            bulletColor.setRed(62).setGreen(103).setBlack(0);
        } else if(GameConstant.MIDDLE.equals(this.level)) {
            enemyCount = 5;
            enemyStep = 3;
            period = 2000;
            bulletColor.setRed(0).setGreen(124).setBlack(124);
        } else if(GameConstant.DIFFICULT.equals(this.level)) {
            enemyCount = 8;
            enemyStep = 4;
            period = 1000;
            bulletColor.setRed(247).setGreen(131).setBlack(131);
        }
    }
}
