import core.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RouteCalculator
{
    private StationIndex stationIndex;

    private static double interStationDuration = 2.5;
    private static double interConnectionDuration = 3.5;


    public RouteCalculator(StationIndex stationIndex)
    {
        this.stationIndex = stationIndex;
    }

    // получение списка станций кротчайшего маршрута
    public List<Station> getShortestRoute(Station from, Station to)
    {
        List<Station> route = getRouteOnTheLine(from, to);
        if(route != null) {
            return route;
        }

        // получаем список следования с одной пересадкой или null
        // в этом методе была ошибка, он возвращал не null, а просто пустой список если не мог определить маршрут
        route = getRouteWithOneConnection(from, to);
        if(route != null) {
            return route;
        }

        route = getRouteWithTwoConnections(from, to);
        return route;
    }


    public static double calculateDuration(List<Station> route)
    {
        double duration = 0;
        Station previousStation = null;
        for(int i = 0; i < route.size(); i++)
        {
            Station station = route.get(i);
            if(i > 0)
            {
                duration += previousStation.getLine().equals(station.getLine()) ?
                    interStationDuration : interConnectionDuration;
            }
            previousStation = station;
        }
        return duration;
    }

    // определим список следования по одной линии.
    private List<Station> getRouteOnTheLine(Station from, Station to)
    {
        if(!from.getLine().equals(to.getLine())) {
            return null;
        }
        // в противном случе,
        ArrayList<Station> route = new ArrayList<>();
        List<Station> stations = from.getLine().getStations(); // берем список всех станций на этой линии
        int direction = 0;  // и определяем направление следования к станции назначения, при нахождении первой из заданных
        for(Station station : stations)
        {
            if(direction == 0)
            {
                if(station.equals(from)) {
                    direction = 1;
                } else if(station.equals(to)) {
                    direction = -1;
                }
            }
            if(direction != 0) {
                route.add(station);
            }

            /*
            если, следуя в положительном направлении мы дошли до станции назначения,
            то мы закрываем цикл, предварительно включив эту станцию в список следования
            то же самое просходит если мы,
            двигаясь в обратном направлении, дошли до станции отправления
            */
            if((direction == 1 && station.equals(to)) ||
                (direction == -1 && station.equals(from))) {
                break;
            }
        }
        if(direction == -1) {
            Collections.reverse(route); // Разворачиваем список если проход был осуществлен в обратном направлении
        }
        return route;
    }

    // список следования с одной пересадкой
    private List<Station> getRouteWithOneConnection(Station from, Station to)
    {
        if(from.getLine().equals(to.getLine())) {
            return null;
        }

        ArrayList<Station> route = new ArrayList<>();

        List<Station> fromLineStations = from.getLine().getStations();
        List<Station> toLineStations = to.getLine().getStations();
        for(Station srcStation : fromLineStations)
        {
            for(Station dstStation : toLineStations)
            {
                if(isConnected(srcStation, dstStation))
                {
                    ArrayList<Station> way = new ArrayList<>();
                    way.addAll(getRouteOnTheLine(from, srcStation));
                    way.addAll(getRouteOnTheLine(dstStation, to));

                    if(route.isEmpty() || route.size() > way.size())
                    {
                        route.clear();
                        route.addAll(way);
                    }
                }
            }
        }
        if(route.isEmpty()){
            return null;
        }
        return route;
    }

    //
    private boolean isConnected(Station station1, Station station2)
    {
        Set<Station> connected = stationIndex.getConnectedStations(station1);
        return connected.contains(station2);
    }

    // получим список следования между промежуточными станциями
    private List<Station> getRouteViaConnectedLine(Station from, Station to)
    {

        Set<Station> fromConnected = stationIndex.getConnectedStations(from);
        Set<Station> toConnected = stationIndex.getConnectedStations(to);
        for(Station srcStation : fromConnected)
        {
            for(Station dstStation : toConnected)
            {
                if(srcStation.getLine().equals(dstStation.getLine()))
                {
                    return getRouteOnTheLine(srcStation, dstStation);
                }
            }
        }
        return null;
    }

    // Получим список следования с двумя пересадками
    private List<Station> getRouteWithTwoConnections(Station from, Station to)
    {
        if (from.getLine().equals(to.getLine())) { //если станции находятся на одной линии вернем null
            return null;
        }

        ArrayList<Station> route = new ArrayList<>();

        List<Station> fromLineStations = from.getLine().getStations();
        List<Station> toLineStations = to.getLine().getStations();
        for(Station srcStation : fromLineStations)
        {
            for (Station dstStation : toLineStations)
            {
                List<Station> connectedLineRoute =
                        getRouteViaConnectedLine(srcStation, dstStation);

                if(connectedLineRoute == null) {
                    continue;
                }
                ArrayList<Station> way = new ArrayList<>();
                way.addAll(getRouteOnTheLine(from, srcStation));

                way.addAll(connectedLineRoute);
                way.addAll(getRouteOnTheLine(dstStation, to));
                if(route.isEmpty() || route.size() > way.size())
                {
                    route.clear();
                    route.addAll(way);
                }
            }
        }

        return route;
    }
}