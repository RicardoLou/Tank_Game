package com.Ricardo.serialize;

import com.Ricardo.bean.BulletColor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class BulletDTO implements Serializable {
    private Integer x;
    private Integer y;
    private String direction;
    private Integer step;
    private Integer speed;
    private BulletColorDTO bulletColor;
    private Integer period;
    private Boolean isAlive = true;
    private volatile Boolean hasInterrupted = false;
}
