package core;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
//done

public class Line {
    private String number;
    private String name;
    @JsonIgnore
    private List<Station> stations;

    public Line(){}
    public Line(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public List<Station> getStations() {
        return stations;
    }
}
