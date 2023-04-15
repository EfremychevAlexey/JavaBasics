import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    public static double coefficient = 0.01;
    public static void main(String[] args) {
        String srcFolder = "C:\\Users\\Admin\\Desktop\\Исходные фото";
        String dstFolder = "C:\\Users\\Admin\\Desktop\\Финальные фото";
        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();
        int middle = files.length / 2;

        File[] files1 = new File[middle];
        System.arraycopy(files, 0, files1, 0, files1.length);
        ImageResizer imageResizer1 = new ImageResizer(files1, coefficient, dstFolder, start);


        File[] files2 = new File[files.length - middle];
        System.arraycopy(files, middle, files2, 0, files2.length);
        ImageResizer imageResizer2 = new ImageResizer(files2, coefficient, dstFolder, start);


        imageResizer1.start();
        imageResizer2.start();
    }
}
