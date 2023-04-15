public class ReverseArray {

  //TODO: Напишите код, который меняет порядок расположения элементов внутри массива на обратный.

  public static String[] reverse(String[] strings) {

    int lengthStrings = strings.length - 1;
    int i = 0;
    while(i < strings.length/2){
      String s = "";
      s = strings[i];
      strings[i] = strings[lengthStrings];
      strings[lengthStrings--] = s;
      i++;
    }
    return strings;
  }
}
