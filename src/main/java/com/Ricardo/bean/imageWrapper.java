package com.Ricardo.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.awt.image.BufferedImage;

@Getter @Setter @Accessors(chain = true)
public class imageWrapper {
    private BufferedImage image;
    private String imageName;
}
