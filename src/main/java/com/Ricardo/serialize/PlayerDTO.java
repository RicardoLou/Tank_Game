package com.Ricardo.serialize;

import lombok.*;

import java.io.Serializable;

@Getter @Setter
public class PlayerDTO extends TankDTO implements Serializable {
    public PlayerDTO() {
    }
}
