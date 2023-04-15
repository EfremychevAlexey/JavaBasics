import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailList {

  TreeSet<String> set = new TreeSet<>();
  ArrayList<String> list = new ArrayList<>();
  // TODO: валидный формат email добавляется

  public void add(String email) {
    Pattern prEmail1 = Pattern.compile("^\\w+@\\w+\\.(ru|com)$");
    Matcher mch1 = prEmail1.matcher(email);
    if (mch1.find()) {
      set.add(email.toLowerCase());
    } else {
      System.out.println(Main.WRONG_EMAIL_ANSWER);
    }
  }

  public List<String> getSortedEmails() {
    // TODO: возвращается список электронных адресов в алфавитном порядке
    list.clear();
    for (String s: set) {
      list.add(s);
    }
    return list;
  }
}
