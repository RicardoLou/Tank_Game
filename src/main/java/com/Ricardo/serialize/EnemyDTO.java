package com.Ricardo.serialize;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Getter @Setter
public class EnemyDTO extends TankDTO implements Serializable {
    private volatile AtomicInteger randomDirectionFlag = new AtomicInteger(0);
    private volatile Boolean hasInterrupted = false;
}
