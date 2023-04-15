import com.skillbox.airport.Airport;
import com.skillbox.airport.Terminal;

public class Experiens {

    public static int countsAircraft = 0;

    public static void main(String[] args) {

        Airport airport = Airport.getInstance();

        Terminal terminal = new Terminal("Пулково");

        countsAircraft = terminal.getParkedAircrafts().size();

        System.out.println(terminal.getParkedAircrafts().size());
    }
}
