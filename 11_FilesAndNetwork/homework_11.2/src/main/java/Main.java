import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String sourceDirectory = "";
        String destinationDirectory = "";

        while (true) {
            System.out.println("В любой момент можно выйти из программы с помощью \"exit\".\n");
            System.out.println("Введите дирректорию, содержание которой нужно скопировать");
            sourceDirectory = scanner.nextLine();
            if (sourceDirectory.equals("exit")) {
                System.out.println("Всего хорошего.");
                break;
            }

            System.out.println("Введите дирректорию, в которую должны быть скопированы файлы");
            destinationDirectory = scanner.nextLine();
            if (destinationDirectory.equals("exit")) {
                System.out.println("Всего хорошего.");
                break;
            }
            FileUtils.copyFolder(sourceDirectory, destinationDirectory);
        }
    }
}
