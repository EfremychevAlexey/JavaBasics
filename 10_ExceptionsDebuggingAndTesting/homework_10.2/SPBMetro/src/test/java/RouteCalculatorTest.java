import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

import java.util.*;

import static org.junit.Assert.*;

public class RouteCalculatorTest extends TestCase {


    StationIndex stationIndex;
    RouteCalculator calculator;

    // пути следования для различных случаев
    List<Station> assertRouteOnTheLine;
    List<Station> assertRouteWithOneConnection;
    List<Station> assertRouteWithTwoConnections;

    List<Station>  stations1;
    List<Station>  stations2;
    List<Station>  stations3;

    @Before
    public void setUp() throws Exception {
        stationIndex = new StationIndex();

        stations1 = new ArrayList<>();
        stations2 = new ArrayList<>();
        stations3 = new ArrayList<>();

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");


        stations1.add(new Station("ПерваяЛинии1", line1));
        stations1.add(new Station("ВтораяЛинии1", line1)); //пересечение со 2 линией
        stations1.add(new Station("ТретьяЛинии1", line1));

        stations2.add(new Station("ПерваяЛинии2", line2));
        stations2.add(new Station("ВтораяЛинии2", line2)); //пересечение с 1 линией
        stations2.add(new Station("ТретьяЛинии2", line2)); //пересечение с 3 линией
        stations2.add(new Station("ЧетвертаяЛинии2", line2));

        stations3.add(new Station("ПерваяЛинии3", line3));
        stations3.add(new Station("ВтораяЛинии3", line3));
        stations3.add(new Station("ТретьяЛинии3", line3)); //пересечение со 2 линией
        stations3.add(new Station("ЧетвертаяЛинии3", line3));

        for(int i = 0; i< stations1.size(); i++){
            line1.addStation(stations1.get(i));
            stationIndex.addStation(stations1.get(i));
        }
        for(int i = 0; i< stations2.size(); i++){
            line2.addStation(stations2.get(i));
            stationIndex.addStation(stations2.get(i));
        }
        for(int i = 0; i< stations3.size(); i++){
            stationIndex.addStation(stations3.get(i));
            line3.addStation(stations3.get(i));
        }

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        List<Station> con1 = new ArrayList<>(); // список сопряженных станций
        List<Station> con2 = new ArrayList<>();

        con1.add(stations1.get(1));
        con1.add(stations2.get(1));
        con2.add(stations2.get(2));
        con2.add(stations3.get(2));

        stationIndex.addConnection(con1);
        stationIndex.addConnection(con2);

        calculator = new RouteCalculator(stationIndex);

        // заполняем актуальный маршрут по одной линии/ маршрут между пересадками
        assertRouteOnTheLine = new ArrayList<>();
        assertRouteOnTheLine.add(stations2.get(1));
        assertRouteOnTheLine.add(stations2.get(2));

        // заполняем актуальный маршрут с одной пересадкой
        assertRouteWithOneConnection = new ArrayList<>();
        assertRouteWithOneConnection.add(stations1.get(0));
        assertRouteWithOneConnection.add(stations1.get(1));
        assertRouteWithOneConnection.add(stations2.get(1));
        assertRouteWithOneConnection.add(stations2.get(2));
        assertRouteWithOneConnection.add(stations2.get(3));

        // заполняем актуальный маршрут с двумя пересадками
        assertRouteWithTwoConnections = new ArrayList<>();
        assertRouteWithTwoConnections.add(stations1.get(0));
        assertRouteWithTwoConnections.add(stations1.get(1));
        assertRouteWithTwoConnections.add(stations2.get(1));
        assertRouteWithTwoConnections.add(stations2.get(2));
        assertRouteWithTwoConnections.add(stations3.get(2));
        assertRouteWithTwoConnections.add(stations3.get(3));

    }
// рассчет времени следования по маршруту с двумя пересадками
    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(assertRouteWithTwoConnections);
        double expected = 14.5;
        assertEquals(actual, expected);
    }
// путь без пересадок
    public void testGetRouteOnTheLine(){
        List<Station> expectedRouteOnTheLine = calculator.getShortestRoute(stations2.get(1), stations2.get(2));
        assertArrayEquals(assertRouteOnTheLine.toArray(), expectedRouteOnTheLine.toArray());
    }
//путь с одной пересадкой
    public void testGetRouteWithOneConnection(){
        List<Station> expectedRouteOnTheLine = calculator.getShortestRoute(stations1.get(0), stations2.get(3));
        assertArrayEquals(assertRouteWithOneConnection.toArray(), expectedRouteOnTheLine.toArray());
    }
//пцть с двумя пересадками
    public void testGetRouteWithTwoConnections(){
        List<Station> expectedRoutegetRouteWithTwoConnections = calculator.getShortestRoute(stations1.get(0), stations3.get(3));
        assertArrayEquals(assertRouteWithTwoConnections.toArray(), expectedRoutegetRouteWithTwoConnections.toArray());
    }

    @After
    public void tearDown() throws Exception {
    }
}