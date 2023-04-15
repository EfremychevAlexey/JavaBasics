public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    //TODO: напишите ваш код, результат вывести в консоль

    String sum = "";
    int totalEarling = 0;
    boolean isDigit;

    for (int i = 0; i < text.length(); i++) {
      isDigit = Character.isDigit(text.charAt(i));

      if (isDigit) {
        sum = sum + text.charAt(i);
      }

      if(!sum.equals("") && !isDigit){
        totalEarling += Integer.parseInt(sum);
        sum = "";
      }

    }
    System.out.println(totalEarling);
  }
}
