import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import core.Line;
import core.Station;

import java.util.*;
import java.util.stream.Collectors;
//done

@JsonPropertyOrder({"stations", "number2line", "connections"})
public class StationIndex {
    @JsonProperty("lines")
    HashSet<Line> number2line = new HashSet<>();
    @JsonProperty("stations")
    TreeMap<String, List<String>> stations = new TreeMap<>();
    @JsonProperty("connections")
    TreeMap<Station, TreeSet<Station>> connections = new TreeMap<>();

    public StationIndex(HashSet<Line> parseLines){
        number2line = parseLines;
        setStations(parseLines);

    }

    private void setStations(Set<Line> parseLines) {
        for (Line l : parseLines){
            stations.put(l.getNumber(), l.getStations().stream().map(station -> station.getName()).collect(Collectors.toList()));
        }
    }

    public Set<Line> getNumber2line() {
        return number2line;
    }

    public TreeMap<String, List<String>> getStations() {
        return stations;
    }

    public TreeMap<Station, TreeSet<Station>> getConnections() {
        return connections;
    }
}
