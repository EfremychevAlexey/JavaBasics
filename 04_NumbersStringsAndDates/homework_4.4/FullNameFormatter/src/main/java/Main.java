import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    String name = "";
    String patronymic = "";
    String family = "";
    int spiceCount = 0;

    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();

    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == ' ') {
        spiceCount++;
        continue;
      }
      if ((int) input.charAt(i) >= 1040 && (int) input.charAt(i) <= 1103 || input.charAt(i) == '-') {
        if (spiceCount == 0) family += input.charAt(i);
        if (spiceCount == 1) name += input.charAt(i);
        if (spiceCount == 2) patronymic += input.charAt(i);
      }
    }

    if(spiceCount == 2 && !family.equals("")) {
      System.out.println("Фамилия: " + family);
      System.out.println("Имя: " + name);
      System.out.println("Отчество: " + patronymic);
    }
    else {
      System.out.println("Введенная строка не является ФИО");
    }
  }
}