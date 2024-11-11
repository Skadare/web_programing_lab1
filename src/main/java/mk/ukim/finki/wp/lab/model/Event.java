package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Event {
    Long id;
    String name;
    String description;
    double popularityScore;
    Location location;

    public Event(String name, String description, double popularityScore, Location location) {
        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
    }
    public Event(){
        this.id = null;
        this.name = null;
        this.description = null;
        this.popularityScore = 0;
        this.location = null;
    }
}
