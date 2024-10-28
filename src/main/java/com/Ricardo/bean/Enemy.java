package com.Ricardo.bean;

import com.Ricardo.config.LevelConfig;
import com.Ricardo.constants.GameConstant;
import com.Ricardo.util.TankUtil;
import com.Ricardo.util.ThreadPoolExecutorUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter @Setter
public class Enemy extends Tank implements Runnable{
    //让其他线程
    private volatile AtomicInteger randomDirectionFlag = new AtomicInteger(0);
    private volatile Boolean hasInterrupted = false;
    public Enemy() {
        setDirection(GameConstant.DIRECTION_DOWN);
        //步长与难度相关
        LevelConfig instance = LevelConfig.getInstance();
        setStep(instance.getEnemyStep());
        setPeriod(instance.getPeriod());
        setSpeed(instance.getSpeed());
        setTankType(GameConstant.TANK_TYPE_ENEMY);
        // 初始化完成以后，对象的images属性就有值了
        populateImages();
    }

    @Override
    public void run() {
        while(true) {
            if (!isAlive) {
                break;
            }
            if (hasInterrupted)
                break;
            //生成子弹
            bornBullet();

            if (randomDirectionFlag.get() <= 0) {
                int random = TankUtil.random(3);
                if(0 == random) {
                    setDirection(GameConstant.DIRECTION_UP);
                } else if (1 == random) {
                    setDirection(GameConstant.DIRECTION_DOWN);
                } else if (2 == random) {
                    setDirection(GameConstant.DIRECTION_LEFT);
                } else if (3 == random) {
                    setDirection(GameConstant.DIRECTION_RIGHT);
                }
                randomDirectionFlag.set(TankUtil.random(100));
            }
            updateCoordinate();
            ThreadPoolExecutorUtil.sleep(50);
            randomDirectionFlag.getAndDecrement();
        }
    }
}
