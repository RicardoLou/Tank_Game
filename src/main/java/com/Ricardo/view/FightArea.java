package com.Ricardo.view;

import com.Ricardo.bean.*;
import com.Ricardo.config.LevelConfig;
import com.Ricardo.constants.GameConstant;
import com.Ricardo.util.ThreadPoolExecutorUtil;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Vector;

@Getter
public class FightArea extends JPanel implements Runnable{
    public Tank player = new Player();
    private Vector<Tank> enemies = new Vector<>();
    private mainView view;

    public FightArea(mainView view) {
        this.view = view;
        this.setBackground(Color.black);
        int width = view.getWidth();
        int offsetX = (width - player.getWidth()) / 2;
        int offsetY = view.getHeight() - player.getHeight() * 2 - 20;
        player.setX(offsetX);
        player.setY(offsetY);
        //敌方坦克
        int enemyCount = LevelConfig.getInstance().getEnemyCount();
        for (int i = 0; i < enemyCount; i++) {
            Tank enemy = new Enemy();
            int enemyOffSetX = (width - enemyCount * enemy.getWidth()) / enemyCount;
            enemy.setX(enemyOffSetX * (i + 1));
            ThreadPoolExecutorUtil.execute(enemy);
            enemies.add(enemy);
        }

        setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //玩家坦克和子弹
        drawBullets(g, player);
        drawTank(g, player);
        //敌方坦克和子弹
        Iterator<Tank> iterator = enemies.iterator();
        while(iterator.hasNext())  {
            Tank enemy = iterator.next();
            if(enemy.getIsAlive()) {
                drawTank(g, enemy);
            } else {
                iterator.remove();
            }
            drawBullets(g, enemy);
        }
    }

    private void drawTank(Graphics g, Tank tank) {
        if (tank.getIsAlive()) {
            g.drawImage(tank.getCurrentDirectionImage(), tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight(), this);
        }
    }
     private void processBomb(Graphics g, Bullet bullet, String tankType) {
        if (GameConstant.TANK_TYPE_PLAYER.equals(tankType)) {
            processPlayerBullet(g, bullet);
        } else {
            processEnemyBullet(g, bullet);
        }
     }

     //处理敌方子弹是否击中玩家
    private void processEnemyBullet(Graphics g, Bullet bullet) {
        if(player.getIsAlive()) {
            judgeBomb(g, bullet, player);
        }
    }

    //处理玩家子弹是否击中敌方
    private void processPlayerBullet(Graphics g, Bullet bullet) {
        Iterator<Tank> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Tank enemy = iterator.next();
            if(!enemy.getIsAlive()) {
                continue;
            }
            judgeBomb(g, bullet, enemy);
        }
    }

    private void judgeBomb(Graphics g, Bullet bullet, Tank tank) {
        boolean hitTarget = bullet.hitTarget(tank);
        if(!hitTarget) {
            return;
        }
        //坦克和子弹都死亡
        if (GameConstant.TANK_TYPE_PLAYER.equals(tank.getTankType())) {
            view.dispose();
            new LoseView();
        }
        tank.setIsAlive(false);
        bullet.setIsAlive(false);
        Vector<BufferedImage> blastImages = Blast.getInstance().getBlastImages();
        for (BufferedImage image : blastImages) {
            g.drawImage(image, tank.getX(), tank.getY(), image.getWidth(), image.getHeight(), this);
        }
    }

    private void drawBullets(Graphics g, Tank tank) {
        Vector<Bullet> bullets = tank.getBullets();
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (bullet.determineBulletIsAlive()) {
                BulletColor bulletColor = bullet.getBulletColor();
                g.setColor(new Color(bulletColor.getRed(), bulletColor.getGreen(), bulletColor.getBlack()));
                g.fillOval(bullet.getX(), bullet.getY(), 7, 7);
                //判断是否被击中了
                processBomb(g, bullet, tank.getTankType());
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            ThreadPoolExecutorUtil.sleep(100);
            //间隔100毫秒自动调用paint，这样避免了paint只在窗口变化才调用
            this.repaint();
        }
    }
}
