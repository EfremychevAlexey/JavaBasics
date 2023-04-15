import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer extends Thread{

    private File[] files;
    private double coefficient;
    private String dstFolder;
    private long start;

    public ImageResizer(File[] files, double coefficient, String dstFolder, long start) {
        this.files = files;
        this.coefficient = coefficient;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {

        try{
            for(File file : files){
                BufferedImage image = ImageIO.read(file);
                if(image == null){
                    continue;
                }

                int newWidth = (int)(image.getWidth() * coefficient);
                int newHeight = (int)(image.getHeight() * coefficient);
                BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;

                for(int x = 0; x < newWidth; x++){
                    for(int y = 0; y < newHeight; y++){
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage,"jpg", newFile);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("Время выполнения: " + ((System.currentTimeMillis() - start)/1000) + " секунд.");
    }
}
