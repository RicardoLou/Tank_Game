package com.Ricardo.loader;

import com.Ricardo.bean.imageWrapper;
import com.Ricardo.constants.GameConstant;
import jdk.internal.util.xml.impl.Input;
import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 加载图片、音视频等素材
 */
@Getter
public class ResourceLoader {
    private BufferedImage informationImage;
    private BufferedImage icon;
    private BufferedImage welcomeImage;
    private ResourceLoader() {
        informationImage = loaderImageWrappers(GameConstant.IMG_OTHER_DIR).get(0).getImage();
        icon = loaderImageWrappers(GameConstant.IMG_ICONS_DIR).get(0).getImage();
        welcomeImage = loaderImageWrappers(GameConstant.IMG_OTHER_DIR).get(1).getImage();
    }
    // 饿汉式单例对象
    private static ResourceLoader resourceLoader = new ResourceLoader();
    public static ResourceLoader getInstance() {
        return resourceLoader;
    }


    public Vector<imageWrapper> loaderImageWrappers(String dir) {
            Vector<imageWrapper> vector = new Vector<>();
            Map<String, InputStream> map = listFiles(dir);
            for (Map.Entry<String, InputStream> entry : map.entrySet()) {
                try {
                    BufferedImage read = ImageIO.read(entry.getValue());
                    imageWrapper wrapper = new imageWrapper();
                    wrapper.setImage(read);
                    wrapper.setImageName(entry.getKey());
                    vector.add(wrapper);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            return vector;
    }

    /**
     * key：文件名 value:文件对应的输入刘
     * @param dir : 从类路径下开始的目录，例如"images/blast"
     * @return
     */
    private Map<String, InputStream> listFiles(String dir) {
        Map<String, InputStream> retMap = new HashMap<>();
        URL url = this.getClass().getClassLoader().getResource(dir);
        try {
            File[] files = new File(url.toURI()).listFiles();
            if(files == null || files.length == 0) {
                return retMap;
            }
            for (File file : files) {
                String name = file.getName();
                String filePath = dir + name;
                InputStream stream = this.getClass().getClassLoader().getResourceAsStream(filePath);
                retMap.put(filePath, stream);
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return retMap;
    }
}
