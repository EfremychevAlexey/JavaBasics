import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    //C:\Users\Admin\Desktop\Новая папка
//C:\Users\Admin\Desktop\Назначение
    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        // TODO: write code copy content of sourceDirectory to destinationDirectory
        Path pS = Paths.get(sourceDirectory);
        Path pD = Paths.get(destinationDirectory);

        try {
            if(!Files.exists(pD)) System.out.println("Копируем файлы в новую директорию: " + Files.createDirectories(pD));
            else System.out.println("Копируем файлы в существующую директорию: " + pD);
            Files.walk(pS).forEach(path -> {
                try {
                    if (path.toFile().isDirectory()) {
                        Files.createDirectories(pD.resolve(pS.relativize(path)));
                    }
                    if (path.toFile().isFile()) {
                        Files.copy(path, pD.resolve(pS.relativize(path)));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }});
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
