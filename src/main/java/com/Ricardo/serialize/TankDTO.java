package com.Ricardo.serialize;

import com.Ricardo.bean.Bullet;
import com.Ricardo.bean.imageWrapper;
import com.Ricardo.constants.GameConstant;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

@Getter @Setter
public class TankDTO implements Serializable {
    protected Integer x = 0;
    protected Integer y = 0;
    protected Integer width = GameConstant.TANK_WIDTH;
    protected Integer height = GameConstant.TANK_HEIGHT;
    protected String direction = GameConstant.DIRECTION_UP;
    protected Boolean isAlive = true;
    protected long lastFireTime = 0;
    protected int period = 1000;
    protected Integer step = 7;
    protected Integer speed = 5;
    protected String tankType = GameConstant.TANK_TYPE_PLAYER;
    protected Map<String, Vector<imageWrapper>> images = new ConcurrentHashMap<>();
    protected Vector<BulletDTO> bullets = new Vector<>();

    public TankDTO() {
    }
}
