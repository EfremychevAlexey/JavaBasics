public class Main {

  public static void main(String[] args) {
    Container container = new Container();
    container.count = 12345;

    int sum = sumDigits(container.count);

    System.out.println(sum);
  }

  public static int sumDigits(Integer number) {

    if(number != null)
    {
      int n = 0;
      String s = Integer.toString(number);

      for(int i = 0; i <= s.length()-1; i++)
      {
        n += Character.getNumericValue(s.charAt(i));
      }
      return n;

    }
    else return -1;
  }
}

// Задание № 3
// n += Integer.parseInt(String.valueOf(s.charAt(i)));

// Задание № 3* (Не понятно что такое редикт, но в данном случае это работает):
// n += Character.digit(s.charAt(i),6);

// Задание № 3*