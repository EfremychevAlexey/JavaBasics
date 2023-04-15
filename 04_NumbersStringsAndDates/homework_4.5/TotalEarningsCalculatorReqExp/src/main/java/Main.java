public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал";
    System.out.println(calculateSalarySum(text));
  }

  public static int calculateSalarySum(String text){
    String[] s = text.split("\\,\\s");
    String sum = "0";
    int totalEarling = 0;

    for (int i = 0; i < s.length; i++) {
      String textReplace = s[i].replaceAll("[^0-9]", "");

      if(!textReplace.isEmpty()){
        sum = s[i].replaceAll("[^0-9]", "");
        totalEarling += Integer.parseInt(sum);
      }
    }

    //TODO: реализуйте метод
    return  totalEarling;
  }
}