package mk.ukim.finki.wp.lab.service.serviceImpl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    @Override
    public Optional<Event> save(String name, String description, Double popularity, Long id) {
        return er.save(name,description, popularity, id);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return er.findById(id);
    }

    @Override
    public Optional<Event> update(Long id, String name, String description, Double popularityScore, Long locationId) {
        return er.update(id, name, description, popularityScore, locationId);
    }

    @Override
    public Optional<Event> delete(Long id) {
        return er.delete(id);
    }
}

