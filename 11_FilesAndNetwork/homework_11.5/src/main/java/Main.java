import com.fasterxml.jackson.databind.ObjectMapper;
import core.Line;
import core.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
//done

public class Main {
    private final static String baseFile = "metroMoscow.json";

    public static void main(String[] args) throws IOException {


        Document document = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
        Elements lines = document.select("div[id=metrodata]"); //Получаем строку со всеми линиями и станциями
        StationIndex stationIndex = new StationIndex(parseLines(lines));

        toJSON(stationIndex);

    }

    public static void toJSON(StationIndex stationIndex) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), stationIndex);
        System.out.println("json created!");
    }

    public static HashSet<Line> parseLines(Elements lines) throws IOException {

        HashSet<Line> set = new HashSet<>();
        Elements l = lines.select("span[data-line]"); //Получаем список всех линий
        for (Element e : l) {
            String numberLine = e
                    .attr("data-line"); // Получаем номер очередной линии
            String nameLine = e
                    .select("span[data-line = " + numberLine + "]").text(); //Получаем название линии по ее номеру
            Line line = new Line(numberLine, nameLine); // создаем объект станции
            Elements stationsOfLine = lines
                    .select("div[data-line = " + numberLine + "]");//Получаем список станций по номеру линии

            line.setStations(parseStationOnList(line, stationsOfLine)); //Парсим станции в список и сохраняем его в экземпляре линии
            set.add(line);
        }
        return set;
    }

    public static List parseStationOnList(Line line, Elements stationsOfLine) {

        List<Station> stationsList = new ArrayList<>();
        Elements idStation = stationsOfLine.select("span[class=num]"); // список номеров станций на данной линии
        Elements nameStation = stationsOfLine.select("span[class=name]"); // список имен станций на данной линии
        for (int i = 0; i < idStation.size(); i++){
            int id = Integer.parseInt(idStation.get(i).text().replaceAll("\\.", ""));
            String name = nameStation.get(i).text();
            stationsList.add(new Station(name, id, line));
        }
        return stationsList;
    }

}
