package com.Ricardo.bean;

import com.Ricardo.config.LevelConfig;
import com.Ricardo.constants.GameConstant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data @NoArgsConstructor @Accessors(chain = true)
public class BulletColor {
    private Integer red;
    private Integer green;
    private Integer black;

    public BulletColor(String tankType) {
        if(GameConstant.TANK_TYPE_PLAYER.equals(tankType)) {
            red = 232;
            green = 168;
            black = 11;
        } else {
            //敌人坦克子弹颜色,不同难度有不同颜色
            BulletColor bulletColor = LevelConfig.getInstance().getBulletColor();
            red = bulletColor.getRed();
            green = bulletColor.getGreen();
            black = bulletColor.getBlack();
        }
    }
}
