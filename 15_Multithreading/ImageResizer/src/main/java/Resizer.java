import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;

public class Resizer extends Thread{

    private File[] files;
    private String dstFolder;
    private double coficient;
    private  long start;

    public Resizer(File[] files, String dstFolder, double coficient, long start) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.coficient = coficient;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                BufferedImage newImage = Scalr.resize(image, (int)(image.getWidth() * coficient), (int)(image.getWidth() * coficient));
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Выполнено за: " + ((System.currentTimeMillis() - start)/1000) + " " + " секунд.");
    }
}
