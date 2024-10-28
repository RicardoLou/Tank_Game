package com.Ricardo.handler;

import com.Ricardo.bean.*;
import com.Ricardo.config.GameConfig;
import com.Ricardo.constants.GameConstant;
import com.Ricardo.serialize.EnemyDTO;
import com.Ricardo.serialize.PersistDTO;
import com.Ricardo.serialize.PlayerDTO;
import com.Ricardo.util.FileUtil;
import com.Ricardo.util.TankUtil;
import com.Ricardo.util.ThreadPoolExecutorUtil;
import com.Ricardo.view.*;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;

@Getter
@Setter
public class mainViewHandler implements ActionListener, KeyListener {
    private mainView view;
    private Tank player;
    private int pauseFlag = 0;
    private String username;

    public mainViewHandler(mainView view, String username) {
        this.view = view;
        FightArea fightArea = view.getFightArea();
        player = fightArea.getPlayer();
        this.username = username;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("information".equals(command)) {
            new informationView(view, username);
        } else if ("pause".equals(command)) {
            Pause(view);
        } else if ("continue".equals(command)) {
            Continue(view);
        } else if ("saveAndExit".equals(command)) {
            //保存坦克的状态到文件
            PersistDTO persistDTO = new PersistDTO();
            // 序列化
            String playerString = JSON.toJSONString(player);
            //反序列化
            PlayerDTO playerDTO = JSON.parseObject(playerString, PlayerDTO.class);
            Vector<Tank> enemies = view.getFightArea().getEnemies();
            Vector<EnemyDTO> enemyDTOS = new Vector<>();
            for (Tank tank : enemies) {
                Enemy enemy = (Enemy) tank;
                String enemyString = JSON.toJSONString(enemy);
                EnemyDTO enemyDTO = JSON.parseObject(enemyString, EnemyDTO.class);
                enemyDTOS.add(enemyDTO);
            }

            persistDTO.setPlayer(playerDTO);
            persistDTO.setEnemies(enemyDTOS);
            FileUtil.write(GameConfig.getInstance().getGameStateCfgFilePath(), JSON.toJSONString(persistDTO));
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // 按键监听
    @Override
    public void keyPressed(KeyEvent e) {
        // 坦克移动 wasd
        String direction = TankUtil.convertCode2Direction(e);
        if (direction != null) {
            player.setDirection(direction);
            player.updateCoordinate();
        }
        // 子弹发射 f
        if (e.getKeyCode() == KeyEvent.VK_F) {
            player.bornBullet();
        }
        if (!GameConstant.PAUSE_FLAG && e.getKeyCode() == 32) {
            Pause(view);
            GameConstant.PAUSE_FLAG = true;
        }
        // CTRL控制继续
        else if (GameConstant.PAUSE_FLAG && e.getKeyCode() == 32) {
            Continue(view);
            GameConstant.PAUSE_FLAG = false;
        }
    }

    private static void Pause(mainView view) {
        Vector<Tank> enemies = view.getFightArea().getEnemies();
        Iterator<Tank> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Tank tank = iterator.next();
            Enemy enemy = (Enemy) tank;
            enemy.setHasInterrupted(true);
            Vector<Bullet> bullets = enemy.getBullets();
            Iterator<Bullet> bulletIterator = bullets.iterator();
            while (bulletIterator.hasNext()) {
                Bullet bullet = bulletIterator.next();
                bullet.setHasInterrupted(true);
            }
        }
    }

    private static void Continue(mainView view) {
        Vector<Tank> enemies = view.getFightArea().getEnemies();
        Iterator<Tank> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Tank tank = iterator.next();
            Enemy enemy = (Enemy) tank;
            enemy.setHasInterrupted(false);
            ThreadPoolExecutorUtil.execute(enemy);
            synchronized (enemy) {
                Vector<Bullet> bullets = enemy.getBullets();
                Iterator<Bullet> bulletIterator = bullets.iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    bullet.setHasInterrupted(false);
                    ThreadPoolExecutorUtil.execute(bullet);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
