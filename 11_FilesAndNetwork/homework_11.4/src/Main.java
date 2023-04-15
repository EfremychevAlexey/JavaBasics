
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Example program to list links from a URL.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String site = "https://www.moscowmap.ru/metro.html#lines";
        String folder = "images";
        Document doc = Jsoup.connect(site).get();
        Elements media = doc.select("img");
        Pattern p = Pattern.compile(".jpe?g$");

        for (Element src : media) {
            String urlImg = src.attr("src");
            Matcher m = p.matcher(urlImg);
             if(m.find()){
                 File file = new File(urlImg);
                 String name = file.getName();
                 downloadUrl(urlImg, name, folder);
             }
        }
    }

    public static void downloadUrl(String url, String name, String folder){

        try {
            InputStream inputStream = URI.create(url).toURL().openStream();
            Path dir = Files.createDirectories(Paths.get(folder));
            Path fileName = dir.resolve(name);
            Files.copy(inputStream, fileName);
            System.out.println(fileName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
