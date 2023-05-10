package PixelMap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;
import java.util.stream.*;

public class PixelImageReader {
    public List<PixelMap> readImages(String parent, String suffix) {
        List<File> files = findFilesWithSuffix(parent, suffix);
        List<PixelMap> list = new ArrayList<>();
        for(File file : files) {
            list.add(this.readImage(file.getPath()));
        }
        return list;
    }

    public PixelMap readImage(String url) {
        int[][] pixels = null;
        try {
            BufferedImage image = ImageIO.read(new File(url));
            int width = image.getWidth();
            int height = image.getHeight();
            pixels = new int[height][width];
            
            // Convert the image to grayscale
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {                    
                    int rgb = image.getRGB(x, y);
                    int gray = (rgb >> 16) & 0xFF;
                    pixels[y][x] = gray;
                }
            }  
        } catch (IOException e) { 
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        return new PixelMap(pixels);
    }

    private static List<File> findFilesWithSuffix(String parent, String suffix) {
        return Arrays.stream(new File(parent).listFiles())
            .filter(file -> file.isFile() && file.getName().endsWith(suffix))
            .collect(Collectors.toList());
    }
}

