import core.Line;
import core.Station;

import java.util.*;
import java.util.stream.Collectors;

public class StationIndex
{
    HashMap<Integer, Line> number2line;
    TreeSet<Station> stations;
    TreeMap<Station, TreeSet<Station>> connections;

    public StationIndex()
    {
        number2line = new HashMap<>();
        stations = new TreeSet<>();
        connections = new TreeMap<>();
    }

    public void addStation(Station station)
    {
        stations.add(station);
    }

    public void addLine(Line line)
    {
        number2line.put(line.getNumber(), line);
    }

    /* Передаем методу список станций одного из узлов.
        Перебираем станции этого узла и для каждой из них создаем
        коллекцию сопряженных с ней станций по принципу
        <ключ-станция, значение-коллекция>
        каждую такую пару <ключ, значение> добавляем в коллекцию connections
    */
    public void addConnection(List<Station> stations)
    {
        for(Station station : stations)
        {
            if(!connections.containsKey(station)) {
                connections.put(station, new TreeSet<>());
            }
            TreeSet<Station> connectedStations = connections.get(station);
            connectedStations.addAll(stations.stream()
                .filter(s -> !s.equals(station)).collect(Collectors.toList()));
        }
    }

    public Line getLine(int number)
    {
        return number2line.get(number);
    }

    public Station getStation(String name)
    {
        for(Station station : stations)
        {
            if(station.getName().equalsIgnoreCase(name)) {
                return station;
            }
        }
        return null;
    }

    /*
    метод возвращает экземпляр станции по заданному имени и линии,
    если такой станции нет, возвращает null
    */
    public Station getStation(String name, int lineNumber)
    {
        Station query = new Station(name, getLine(lineNumber));
        Station station = stations.ceiling(query);
        return station.equals(query) ? station : null;
    }

    /*
    метод возвращает коллекцию станций сопряженных с заданной либо тустую коллекцию.
    */
    public Set<Station> getConnectedStations(Station station)
    {
        if(connections.containsKey(station)) {

            return connections.get(station);
        }
        return new TreeSet<>();
    }

}
