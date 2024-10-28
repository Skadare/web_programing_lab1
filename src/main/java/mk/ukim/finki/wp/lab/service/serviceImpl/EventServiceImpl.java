package mk.ukim.finki.wp.lab.service.serviceImpl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    final EventRepository er;

    public EventServiceImpl(EventRepository er) {
        this.er = er;
    }

    @Override
    public List<Event> listAll() {
        return er.findAll();
    }

    @Override
    public List<Event> searchEvents(String text, Double rating) {
        return er.searchEvents(text,rating);
    }
}
