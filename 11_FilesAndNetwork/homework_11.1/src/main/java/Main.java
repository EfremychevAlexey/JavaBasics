import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String path = "";

        while(true){
            System.out.println("Введите путь к папке если нужно расчитать ее общий размер \n" +
                    "или введите \"exit\" для выхода из программы.");
            path = scanner.nextLine();
            if(path.equals("exit")){
                break;
            }
            long size = 0;
            size = FileUtils.calculateFolderSize(path);
            System.out.print("Размер папки: " + path + " составляет:");
            toString(size);
        }
    }
    public static void toString(long size){

        if (size / 1024 / 1024 > 999) {
            System.out.printf("%.1f", size / 1024.0 / 1024.0 / 1024.0);
            System.out.println(" ГБ");
        } else if (size / 1024 / 1024 > 0) {
            System.out.printf("%.1f", size / 1024.0 / 1024.0);
            System.out.println(" МБ");
        } else if (size / 1024 > 0) {
            System.out.printf("%.1f", size / 1024.0);
            System.out.println(" КБ");
        } else {
            System.out.printf(size + " Байт");
        }
        System.out.println();
    }
}
