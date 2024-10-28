package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    List<Event> events = new ArrayList<>();

    public EventRepository()
    {
        events.add(new Event("Tech Innovators Conference","Showcasing tech advancements",9.2));
        events.add(new Event("Gourmet Food Festival","Culinary delights from chefs",8.7));
        events.add(new Event("Summer Music Carnival","Live music performances outdoors",3.0));
        events.add(new Event("AI and Robotics Expo","Cutting-edge AI and robots",7.5));
        events.add(new Event("Marathon for a Cause","Run for environmental conservation", 9.8));
        events.add(new Event("Art and Craft Fair","Handmade crafts and art",6.0));
        events.add(new Event("Film Screening Night","Classic movie showing event",7.0));
        events.add(new Event("Entrepreneurship Summit","Insights for aspiring entrepreneurs",8.0));
        events.add(new Event("Cultural Heritage Parade","Celebrating diverse cultural traditions",9.0));
        events.add(new Event("Fitness Bootcamp Weekend","Intense outdoor fitness training",7.4));
    }

    public List<Event> findAll(){
        return events;
    }
    public List<Event> searchEvents(String text, Double rating){
        return events.stream().filter(x-> (text==null || x.getName().contains(text) || x.getDescription().contains(text)) && (rating==null || x.getPopularityScore()>=rating)).collect(Collectors.toList());
    }

}
