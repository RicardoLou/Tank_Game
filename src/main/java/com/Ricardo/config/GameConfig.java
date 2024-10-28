package com.Ricardo.config;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class GameConfig {
    private String gameStateCfgFilePath;
    public GameConfig() {

    }
    private static GameConfig gameConfig = new GameConfig();
    public static GameConfig getInstance() {
        return gameConfig;
    }
}
