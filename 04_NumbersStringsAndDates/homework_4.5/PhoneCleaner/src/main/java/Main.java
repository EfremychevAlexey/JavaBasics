import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    while (true) {
      String input = scanner.nextLine().replaceAll("[^0-9]","");
      if (input.equals("0")) {
        break;
      }
      String newInput = input;

      if(input.length() >= 10 && input.length() <= 11){

        if(input.length() == 10 && input.charAt(0) == '9'){
          System.out.println("7" + input);
          break;
        }
        if(input.length() == 10 && input.charAt(0) != '9'){
          System.out.println("Неверный формат номера");
          break;
        }
        if (input.length() == 11 && input.charAt(1) == '9'){
          System.out.print("7");
          for (int i = 1; i < input.length(); i++){
            System.out.print(input.charAt(i));
          }
        }
        else System.out.println("Неверный формат номера");
        break;
      }
      else System.out.println("Неверный формат номера");
      break;
    }
  }
}





      //TODO:напишите ваш код тут, результат вывести в консоль.