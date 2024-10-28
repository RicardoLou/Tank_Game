package com.Ricardo.util;

import com.Ricardo.constants.GameConstant;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
    public static void createDirIfNecessary(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdir();
        }
    }
    public static void createFileIfNecessary(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String read(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
    public static void write(String filePath, String content) {
        try {
            FileUtils.writeStringToFile(new File(filePath), content, GameConstant.ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
