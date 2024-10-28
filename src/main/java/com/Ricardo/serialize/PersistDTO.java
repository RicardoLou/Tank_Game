package com.Ricardo.serialize;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Vector;

@Setter @Getter
public class PersistDTO implements Serializable {
    private PlayerDTO player;
    private Vector<EnemyDTO> enemies;
    public PersistDTO(){

    }
}
