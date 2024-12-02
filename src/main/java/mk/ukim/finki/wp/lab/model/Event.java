package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    Double popularityScore;
    @ManyToOne
    Location location;
    @ManyToOne
    Category category;
    boolean liked;


    public Event(String name, String description, Double popularityScore, Location location, Category category) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
        this.category = category;
        this.liked = false;
    }
    public Event(){
        this.id = null;
        this.name = null;
        this.description = null;
        this.popularityScore = 0.0;
        this.location = null;
        this.category = null;
        this.liked = false;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
