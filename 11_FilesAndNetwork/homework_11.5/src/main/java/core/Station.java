package core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
//done

public class Station {
    @JsonIgnore
    private Line line;
    @JsonIgnore
    private int id;
    private String name;

    public Station(){}
    public Station(String name, int id, Line line)
    {
        this.name = name;
        this.id = id;
        this.line = line;
    }

    public Line getLine() {
        return line;
    }

    public String getName() {
        return name;
    }
}
