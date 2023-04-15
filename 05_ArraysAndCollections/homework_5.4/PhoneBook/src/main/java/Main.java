import java.util.Scanner;
//lesson done
public class Main {

  public static void main(String[] args) {
    PhoneBook phoneBook = new PhoneBook();
    Scanner scn = new Scanner(System.in);

    while (true) {
      String arg = scn.nextLine();
      if (arg.equals("0")) {
        break;
      }
      if (arg.contains("LIST")) {
        for (String s : phoneBook.getAllContacts()) {
          System.out.println(s);
        }
      }

      else if (phoneBook.hasNumber(arg)) {
        String hasName = phoneBook.getNameByPhone(arg);
        if (hasName.equals("")) {
          phoneBook.printNotNum(arg);
          String nameForNewNum = scn.nextLine();
          phoneBook.addContact(arg, nameForNewNum);
        }
        else System.out.println(hasName);
      }

      else if (phoneBook.hasName(arg)) {
        if (phoneBook.getPhonesByName(arg).isEmpty()) {
          phoneBook.printNotName(arg);
          String numberForNewName = scn.nextLine();
          phoneBook.addContact(numberForNewName, arg);
        }
        else {
          for(String s: phoneBook.getPhonesByName(arg)){
            System.out.println(s);
          }
        }
      }
    }
  }
}
