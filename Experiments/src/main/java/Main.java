import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

  public static void main(String[] args) throws IOException {

    String child = "courses";
    String url = "https://skillbox.ru";
    String newUrl = "https://skillbox.ru/courses/vzaimodeistvie-s-zakazchikom";

    Set<String> childLinks = new TreeSet<>();
    Document document = Jsoup.connect(newUrl).maxBodySize(0).get();
    Elements elements = document.select("a");

    for (Element el : elements) {
      String element = el.absUrl("href");
      if (element.contains(url)) {
        String[] links = element.replaceAll(url, "")
            .replaceFirst("/", "").split("/");

        if (links.length > 0) {
          String s = links[0].trim();

          if(s.contains("?") || s.contains("#") || s.contains("=")) {
            continue;
          }

          if (!childLinks.contains(s) && !s.isEmpty()) {
            childLinks.add(s);
          }
        }
      }
    }
    childLinks.forEach(System.out::println);
  }
}
