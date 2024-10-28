package com.Ricardo.constants;

/**
 * 一些常量
 */
public class GameConstant {
    //难度级别
    public static final String EASY = "easy";
    public static final String MIDDLE = "middle";
    public static final String DIFFICULT = "difficult";

    //资源文件位置
    public static final String IMG_ABOUT_DIR = "images/about/";
    public static final String AUDIO_DIR = "audio/";
    public static final String IMG_BLAST_DIR = "images/blast/";
    public static final String IMG_ENEMY_EASY_DIR = "images/enemies/easy/";
    public static final String IMG_ENEMY_MIDDLE_DIR = "images/enemies/middle/";
    public static final String IMG_ENEMY_DIFFICULT_DIR = "images/enemies/difficult/";
    public static final String IMG_ICONS_DIR = "images/icons/";
    public static final String IMG_PLAYER_DIR = "images/player/";
    public static final String IMG_WORD_DIR = "images/word/";
    public static final String IMG_OTHER_DIR = "images/other/";

    // 方向
    public static final String DIRECTION_UP = "u";
    public static final String DIRECTION_DOWN = "d";
    public static final String DIRECTION_LEFT = "l";
    public static final String DIRECTION_RIGHT = "r";

    //坦克宽高
    public static final Integer TANK_WIDTH = 60;
    public static final Integer TANK_HEIGHT= 60;
    public static final String TANK_TYPE_PLAYER = "player";
    public static final String TANK_TYPE_ENEMY = "enemy";
    public static Integer fireAreaWidth = 1000;
    public static Integer fireAreaHeight = 1000;
    //其他常量
    public static Boolean PAUSE_FLAG = false;
    public static String cfgDir = "";
    //字符编码
    public static final String ENCODING = "utf-8";
}
