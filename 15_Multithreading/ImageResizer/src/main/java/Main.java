import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    static double coeficient = 0.5;

    public static void main(String[] args) {
        String srcFolder = "C:\\Users\\Admin\\Desktop\\Исходные фото";
        String dstFolder = "C:\\Users\\Admin\\Desktop\\Финальные фото";
        int cores = Runtime.getRuntime().availableProcessors();
        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();


        int countImgForThread = files.length / cores;
        File[] files1;
        for(int i = 0; i < cores; i++){
            if(i == cores - 1){
                files1 = new File[files.length - (countImgForThread * i)];
            }
            else {
                files1 = new File[countImgForThread];
            }
            System.arraycopy(files,(countImgForThread * i), files1,0, files1.length);
            new Resizer(files1, dstFolder, coeficient, System.currentTimeMillis()).start();
        }
    }
}
