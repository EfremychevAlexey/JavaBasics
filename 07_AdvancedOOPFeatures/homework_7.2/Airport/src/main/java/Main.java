import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        Calendar now = new GregorianCalendar();
        Calendar afterTwoHours = new GregorianCalendar();
        afterTwoHours.add(Calendar.HOUR_OF_DAY,2);


        return airport.getTerminals().stream()      // Создаем стрим из списка терминалов
                .map(Terminal::getFlights)          // преобразовываем Stream<Terminal> в Stream<List<Flight>>
                .flatMap(Collection::stream)        // разворачиваем Stream<List<Flight>> в Stream<Flight>
                .filter(flight -> flight.getType()
                        .equals(Flight.Type.DEPARTURE))         //оставляем только отправляющиеся рейсы
                .filter(flight -> flight.getDate()
                        .after(now.getTime()))                  // оставляем рейсы ожидающие отправления
                .filter(flight -> flight.getDate()
                        .before(afterTwoHours.getTime()))   //оставляем рейсы с отправлением не позднее чем через 2 часа.
                .collect(Collectors.toList());              // одобавляем оставшиеся рейсы в список.
    }

}