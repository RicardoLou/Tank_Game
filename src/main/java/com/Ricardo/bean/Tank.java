package com.Ricardo.bean;

import com.Ricardo.config.LevelConfig;
import com.Ricardo.constants.GameConstant;
import com.Ricardo.loader.ResourceLoader;
import com.Ricardo.util.ThreadPoolExecutorUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;

import java.awt.*;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

@Getter @Setter
public abstract class Tank implements Runnable{
    protected Integer x = 0;
    protected Integer y = 0;
    protected Integer width = GameConstant.TANK_WIDTH;
    protected Integer height = GameConstant.TANK_HEIGHT;
    // 方向
    protected String direction = GameConstant.DIRECTION_UP;
    // 标识是否还活着
    protected Boolean isAlive = true;
    // 上次发射时间
    protected long lastFireTime = 0;
    protected int period = 1000; // 默认间隔一秒
    // 坦克移动步长
    protected Integer step = 7; // px
    protected Integer speed = 5; // 子弹射速
    protected String tankType = GameConstant.TANK_TYPE_PLAYER;
    // 并发图片 key:player or enemy value:每个key对应的四张图片
    protected Map<String, Vector<imageWrapper>> images = new ConcurrentHashMap<>();
    //子弹
    protected Vector<Bullet> bullets = new Vector<>();
    public Tank() {

    }
    /**
     *
     * @return ： 返回当前坦克当前方向的图片
     */
    public Image getCurrentDirectionImage() {
        String key = GameConstant.TANK_TYPE_PLAYER;
        if(this instanceof Enemy) {
            //当前难度级别
            key = LevelConfig.getInstance().getLevel();
        }
        Vector<imageWrapper> vector = this.images.get(key);
        for (imageWrapper wrapper : vector) {
            String fileBaseName = FilenameUtils.getBaseName(wrapper.getImageName());
            if (fileBaseName.endsWith(this.direction)) {
                return wrapper.getImage();
            }
        }
        return null;
    }

    //填充image的值
    protected void populateImages() {
        try {
            String key = GameConstant.TANK_TYPE_PLAYER;
            String imageDir = GameConstant.IMG_PLAYER_DIR;
            if(GameConstant.TANK_TYPE_ENEMY.equals(this.tankType)) {
                imageDir = GameConstant.IMG_ENEMY_EASY_DIR;
                String level = LevelConfig.getInstance().getLevel();
                key = level;
                if (GameConstant.MIDDLE.equals(level)) {
                    imageDir = GameConstant.IMG_ENEMY_MIDDLE_DIR;
                } else if(GameConstant.DIFFICULT.equals(level)) {
                    imageDir = GameConstant.IMG_ENEMY_DIFFICULT_DIR;
                }
            }
            Vector<imageWrapper> imageWrappers = ResourceLoader.getInstance().loaderImageWrappers(imageDir);
            this.images.put(key, imageWrappers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCoordinate() {
        if (GameConstant.DIRECTION_UP.equals(this.direction)) {
            if (y - step > 0)
                this.setY(y - step);
        } else if(GameConstant.DIRECTION_LEFT.equals(this.direction)) {
            if (x - step > 0)
                this.setX(x - step);
        } else if(GameConstant.DIRECTION_DOWN.equals(this.direction)) {
            if (y + step < GameConstant.fireAreaHeight - GameConstant.TANK_HEIGHT)
                this.setY(y + step);
        } else if(GameConstant.DIRECTION_RIGHT.equals(this.direction)) {
            if (x + step < GameConstant.fireAreaWidth - GameConstant.TANK_WIDTH)
                this.setX(x + step);
        }
    }

    public void bornBullet() {
        if (System.currentTimeMillis() - lastFireTime > period) {
            Bullet bullet = new Bullet(this);
            ThreadPoolExecutorUtil.execute(bullet);
            synchronized (this) {
                this.bullets.add(bullet);
                lastFireTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void run() {

    }
}