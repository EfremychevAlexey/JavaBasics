import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static long calculateFolderSize(String path) {

        try{
            return Files.walk(Paths.get(path)).
                    filter(p -> !p.toFile().isDirectory())
                    .mapToLong(p -> p.toFile().length()).reduce((p1,p2) -> p1+p2).getAsLong();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    /*
    public static long calculateFolderSize(String path) {
        File folder = new File(path);
        folder.length();
        File[] files = folder.listFiles();
        long size = 0L;

        for(int i = 0; i < files.length; i++){
            if(files[i].isDirectory()){
                size += getFolderSize(files[i]);
            } else{
                size += files[i].length();
            }
        }
        return size;
    }

    public static long getFolderSize(File folder){
        File[] files = folder.listFiles();
        long sizeFolder = 0L;
        for(int i = 0; i < files.length; i++){
            if(files[i].isDirectory()){
                sizeFolder += getFolderSize(files[i]);
            } else {
                sizeFolder += files[i].length();
            }
        }
        return sizeFolder;
    }
    */

}
