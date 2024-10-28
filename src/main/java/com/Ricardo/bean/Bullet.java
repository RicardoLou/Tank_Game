package com.Ricardo.bean;

import com.Ricardo.constants.GameConstant;
import com.Ricardo.util.ThreadPoolExecutorUtil;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Bullet implements Runnable{
    private Integer x;
    private Integer y;
    private String direction;
    private Integer step;
    private Integer speed;
    private BulletColor bulletColor;
    private Integer period;
    private Boolean isAlive = true;
    private volatile Boolean hasInterrupted = false;

    public Bullet() {
    }

    public Bullet(Tank attachTank) {
        this.step = attachTank.getStep();
        this.direction = attachTank.getDirection();
        this.speed = attachTank.speed;
        this.period = attachTank.period;
        this.bulletColor = new BulletColor(attachTank.getTankType());
        // todo : x, y 已完成 待微调
        switch (direction) {
            case "u":
                x = attachTank.getX() + 27;
                y = attachTank.getY() - 8;
                break;
            case "l":
                x = attachTank.getX() - 8;
                y = attachTank.getY() + 15;
                break;
            case "d":
                x = attachTank.getX() + 27;
                y = attachTank.getY() + 60;
                break;
            case "r":
                x = attachTank.getX() + 65;
                y = attachTank.getY() + 15;
                break;
            default:
                break;
        }
    }

    public boolean determineBulletIsAlive() {
        if(!isAlive) {
            //死亡
            return false;
        } else {
            switch (direction) {
                case "u":
                    isAlive = y > 0;
                    break;
                case "l":
                    isAlive = x > 0;
                    break;
                case "d":
                    isAlive = y < GameConstant.fireAreaHeight;
                    break;
                case "r":
                    isAlive = x < GameConstant.fireAreaWidth;
                    break;
                default:
                    break;
            }
        }
        return isAlive;
    }

    // todo：子弹多线程
    @Override
    public void run() {
        while (true) {
            if (hasInterrupted)
                break;
            ThreadPoolExecutorUtil.sleep(50);
            if (determineBulletIsAlive()) {
                switch (direction) {
                    case "u":
                        y -= speed;
                        break;
                    case "l":
                        x -= speed;
                        break;
                    case "d":
                        y += speed;
                        break;
                    case "r":
                        x += speed;
                        break;
                    default:
                        break;
                }
            } else {
                break;
            }
        }
    }

    //判断是否击中了目标
    public boolean hitTarget(Tank target) {
        int minX = target.getX();
        int maxX = minX + target.getWidth();
        int minY = target.getY();
        int maxY = minY + target.getHeight();
        return x > minX && x < maxX && y > minY && y < maxY;
    }
}