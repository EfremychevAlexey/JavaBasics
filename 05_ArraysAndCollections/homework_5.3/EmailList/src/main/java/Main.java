import java.util.Scanner;

public class Main {

  public static final String WRONG_EMAIL_ANSWER = "Неверный формат email";
  public static void main(String[] args) {
    EmailList emailList = new EmailList();
    Scanner scanner = new Scanner(System.in);

    while (true) {
      String input = scanner.nextLine();
      String[] text = input.split("\\s", 2);

      if (input.equals("0")) {
        break;
      } else if (text.length == 2) {
        if (text[0].equals("ADD")) {
          emailList.add(text[1]);
        } else {
          System.out.println(WRONG_EMAIL_ANSWER);
        }
      } else if (text.length == 1) {
        if (text[0].equals("LIST")) {
          for (String s : emailList.getSortedEmails()) {
            System.out.println(s);
          }
        }
      } else {
        System.out.println(WRONG_EMAIL_ANSWER);
      }
    }
  }
}

    /* TODO:
        Пример вывода списка Email, после ввода команды LIST в консоль:
        test@test.com
        hello@mail.ru
        - каждый адрес с новой строки
        - список должен быть отсортирован по алфавиту
        - email в разных регистрах считается одинаковыми
           hello@skillbox.ru == HeLLO@SKILLbox.RU
        - вывод на печать должен быть в нижнем регистре
           hello@skillbox.ru
        Пример вывода сообщения об ошибке при неверном формате Email:
        "Неверный формат email"
    */
//TODO: write code here