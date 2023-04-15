import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String text = "Номер паспорта <<45 0> <8 361> 513";
        String placeholder = "***";

        System.out.println(searchAndReplaceDiamonds(text, placeholder));
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений

        return text.replaceAll("<.+?>", placeholder);

        /* Ввиду закрепления решение через поисковик:
        Pattern pattern = Pattern.compile("<.+?>");
        Matcher matcher = pattern.matcher(text);
        matcher.replaceAll(placeholder);
        */

    }
}
