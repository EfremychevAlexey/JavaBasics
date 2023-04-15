import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите количество ящиков");
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int boxesInt = Integer.parseInt(boxes);
        int container = 0;
        int truck = 0;

        int containerCount = 1;
        int truckCount = 1;

        int boxCount = 1;

        while (boxesInt > 0){
            if((boxCount - 1) % 27 == 0){
                container++;

                if((container - 1) % 12 == 0){
                    truck++;
                    System.out.println("Грузовик: " + truck);
                }

                System.out.println("\tКонтейнер: " + container);
            }
            System.out.println("\t\tЯщик: " + (boxCount++));
            boxesInt--;
        }
        System.out.println("Необходимо:\nгрузовиков - " + truck + " шт.\nконтейнеров - " + container + " шт.");
    }

}
