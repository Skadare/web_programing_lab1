package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    @OneToMany(mappedBy = "category")
    List<Event> eventList;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Category(){}
}
