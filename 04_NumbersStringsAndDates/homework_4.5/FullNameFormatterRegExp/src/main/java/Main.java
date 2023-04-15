import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      String input = scanner.nextLine();
      int count = 2;

      if (input.equals("")) {
        System.out.println("Введенная строка не является ФИО");
        break;
      }

      for (int i = 0; i < input.length(); i++) {
        int symbol = (int) input.charAt(i);
        if (symbol == ' ') count--;
        if (!(symbol >= 1040 && symbol <= 1103 || symbol == '-' || symbol == ' ')) {
          count = -1;
        }
      }


      if (count != 0) {
        System.out.println("Введенная строка не является ФИО");
        break;
      }


      if (count == 0) {
        String[] inputList = input.split("[\\s]");
        System.out.println("Фамилия: " + inputList[0]);
        System.out.println("Имя: " + inputList[1]);
        System.out.println("Отчество: " + inputList[2]);
        break;
      }
    }
  }
}
