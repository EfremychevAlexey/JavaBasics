import core.Line;
import core.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    private  static Logger loggerError;
    private  static Logger loggerInfo;
    private  static Logger loggerEx;

    private static String dataFile = "src/main/resources/map.json";
    private static Scanner scanner;

    private static StationIndex stationIndex;

    public static void main(String[] args)
    {

        RouteCalculator calculator = getRouteCalculator();

        loggerError = LogManager.getLogger("errorLog");
        loggerInfo = LogManager.getLogger("infoLog");
        loggerEx = LogManager.getLogger("exLog");

        System.out.println("Программа расчёта маршрутов метрополитена Санкт-Петербурга\n");
        scanner = new Scanner(System.in);
        for(;;)
        {
            try{
                Station from = takeStation("Введите станцию отправления:");
                loggerInfo.info("Запрошена станция отправления: " + from.getName());
                Station to = takeStation("Введите станцию назначения:");
                loggerInfo.info("Запрошен маршрут до станции " + to.getName());

                List<Station> route = calculator.getShortestRoute(from, to);
                System.out.println("Маршрут:");
                printRoute(route);

                System.out.println("Длительность: " +
                        RouteCalculator.calculateDuration(route) + " минут");
            } catch(Exception ex){
                loggerEx.debug(ex.getMessage());
            }

        }
    }

    private static RouteCalculator getRouteCalculator()
    {
        createStationIndex();
        return new RouteCalculator(stationIndex);
    }

    private static void printRoute(List<Station> route)
    {
        Station previousStation = null;
        for(Station station : route)
        {
            if(previousStation != null)
            {
                Line prevLine = previousStation.getLine();
                Line nextLine = station.getLine();
                if(!prevLine.equals(nextLine))
                {
                    System.out.println("\tПереход на станцию " +
                        station.getName() + " (" + nextLine.getName() + " линия)");
                }
            }
            System.out.println("\t" + station.getName());
            previousStation = station;
        }
    }

    private static Station takeStation(String message)
    {
        for(;;)
        {
            System.out.println(message);
            String line = scanner.nextLine().trim();
            Station station = stationIndex.getStation(line);
            if(station != null) {
                return station;
            }
            loggerError.error("Станция не найдена: " + line);
            System.out.println("Станция не найдена :(");
        }
    }

    private static void createStationIndex()
    {
        stationIndex = new StationIndex();
        try
        {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            parseLines(linesArray);

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject);

            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            parseConnections(connectionsArray);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void parseConnections(JSONArray connectionsArray)
    {
        connectionsArray.forEach(connectionObject -> // перебираем каждый узел
        {
            JSONArray connection = (JSONArray) connectionObject; //создаем массив станций рассматриваемого узла
            List<Station> connectionStations = new ArrayList<>(); // создаем временный список

            connection.forEach(item -> //перебираем станции рассматриваемого узла
            {
                JSONObject itemObject = (JSONObject) item; // создаем HashMap очередной станции узла
                int lineNumber = ((Long) itemObject.get("line")).intValue(); //получаем номер линии рассматриваемой станции
                String stationName = (String) itemObject.get("station"); //получаем название рассматриваемой станции

                Station station = stationIndex.getStation(stationName, lineNumber); // проверяем существует ли
                // станция на такой-то линии и с таким-то названием
                if(station == null) //если такой нет, то ловим исключение
                {
                    throw new IllegalArgumentException("core.Station " +
                        stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station); //если есть добавляем ее во временный список
                //переходим к следующей станции узла
            });
            //перебрав все станции узла, передаем список классу StationIndex
            stationIndex.addConnection(connectionStations);  //
            //далее переходим к следующему узлу
        });
    }

    private static void parseStations(JSONObject stationsObject) //создает полный список станций и
    // заполняет список станций каждой линии
    {
        stationsObject.keySet().forEach(lineNumberObject -> //перебираем множество ключей (номеров линий)
        {
            int lineNumber = Integer.parseInt((String) lineNumberObject); //парсим ключ в int
            Line line = stationIndex.getLine(lineNumber); //получаем линию по ключу номеру
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject); //получаем значение строку по ключу(номеру линии)
            // и преобразуем ее в массив объектов строк (названия станций)
            stationsArray.forEach(stationObject -> // перебираем каждый элемент массива данной линии
            {
                Station station = new Station((String) stationObject, line); //создаем с помощью конструктора новую станцию
                stationIndex.addStation(station); //добавляем ее в коллекцию stations класса StationIndex
                line.addStation(station); //добавляем ее в коллекцию stations определенной линии класса Line
            });
        });
    }

    private static void parseLines(JSONArray linesArray) // создает список содержащий все линии
    {
        linesArray.forEach(lineObject -> { //перебираем каждую строку массива
            JSONObject lineJsonObject = (JSONObject) lineObject; //преобразуем строку в HashMap<Object, Object>
            Line line = new Line(
                    ((Long) lineJsonObject.get("number")).intValue(), //преобразуем значени объект по ключу "number" в примитив int
                    (String) lineJsonObject.get("name") // преобразуем объект значение по ключу "name" в тип String
            ); //создаём новый объект линии
            stationIndex.addLine(line); //добавляем линию в коллекцию HashMap<Integer, Line> number2line.
        });
    }

    private static String getJsonFile() // метод преобразует файл map.json в строку и возвращает ее
    {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(dataFile));
            lines.forEach(line -> builder.append(line));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}