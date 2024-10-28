package com.Ricardo.bean;

import com.Ricardo.constants.GameConstant;
import com.Ricardo.loader.ResourceLoader;
import lombok.Getter;
import org.apache.commons.io.FilenameUtils;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class Blast {
    private Vector<BufferedImage> blastImages;
    private Blast() {
        blastImages = new Vector<>();
        Vector<imageWrapper> imageWrappers = ResourceLoader.getInstance().loaderImageWrappers(GameConstant.IMG_BLAST_DIR);
        //按文件名升序排序
        imageWrappers.sort((o1, o2) -> {
            int o1Name = Integer.parseInt(FilenameUtils.getBaseName(o1.getImageName()));
            int o2Name = Integer.parseInt(FilenameUtils.getBaseName(o2.getImageName()));
            return o2Name - o1Name;
        });
        for (imageWrapper imageWrapper : imageWrappers) {
            blastImages.add(imageWrapper.getImage());
        }
    }
    private static Blast blast = new Blast();
    public static Blast getInstance() {
        return blast;
    }
    public Vector<BufferedImage> getBlastImages() {
        return blastImages;
    }
}
