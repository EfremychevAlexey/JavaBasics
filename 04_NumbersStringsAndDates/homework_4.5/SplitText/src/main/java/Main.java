public class Main {

  public static void main(String[] args) {

    int count = 0;
    String text = "String[] split(String regex, int limit): This Java String split method is used when we want the substrings " +
            "to be limited. The only difference between this method and above method is that it limits the number of strings " +
            "returned after split up. For e.g. split(\"anydelimiter\", 3) would return the array of only 3 strings even if the " +
            "delimiter is present in the string more than 3 times. " +
            "If the limit is negative then the returned array would be having as many substrings as possible however when " +
            "the limit is zero then the returned array would be having all the substrings excluding the trailing empty Strings.";

    for (int i = 0; i < text.length(); i++) {
      if (text.charAt(i) == ' ') {
        count++;
      }
      if (text.charAt(i) == '(') {
        count++;
      }
    }

    if (count >= 100) System.out.println(splitTextIntoWords(text));
  }

  public static String splitTextIntoWords(String text) {
    String textReplace = text.replaceAll("[\\.\\,\\)\\[\\]\\:\\).\"\\s+\\(]", " ");
    String[] splitText = textReplace.split("\\s+");
    String newText = "";
    for (String s: splitText){
      newText = newText + s + "\n";
    }

    return newText;
  }
}