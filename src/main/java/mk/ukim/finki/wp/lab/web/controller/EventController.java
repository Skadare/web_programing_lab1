package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.service.CategoryService;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/events")
@Controller
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;
    private final CategoryService categoryService;

    public EventController(EventService eventService, LocationService locationService, CategoryService categoryService) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String text,
                                @RequestParam(required = false) Double rating,
                                @RequestParam(required = false) String location,
                                Model model){
        List<Event> eventList;
        if(text!=null && rating!=null){
           eventList = eventService.searchEvents(text,rating);
        }
        else if(location!=null){
            Location location1 = locationService.findByName(location).orElseThrow(RuntimeException::new);
            eventList = eventService.findByLocId(location1.getId());
        }
        else {
            eventList = eventService.listAll();
        }
        model.addAttribute("events",eventList);
        return "listEvents";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAdd(Model model){
        model.addAttribute("locations",locationService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("event", new Event());
        return "addEvent";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEditEvent(@PathVariable Long id, Model model){
        Event event = eventService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid id"+id));
        model.addAttribute("event",event);
        model.addAttribute("locations",locationService.findAll());
        model.addAttribute("categories",categoryService.findAll());

        return "addEvent";
    }

    @PostMapping("/saveEdit")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEdited(@RequestParam(required = false) Long id,
                             @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam double popularity,
                             @RequestParam Long locationId,
                             @RequestParam Long categoryId
                             ) {

        if(id != null){
            eventService.update(id, name, description, popularity, locationId, categoryId);
        }
        else{
            eventService.save(name, description, popularity, locationId, categoryId);
        }

        return "redirect:/events";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEvent(@PathVariable Long id){
        eventService.delete(id);
        return "redirect:/events";
    }

    @PostMapping("/increase/{id}")
    public String increaseEvent(@PathVariable Long id, Model model){
        eventService.increasePopularity(id);
        Event event =  eventService.findById(id).orElseThrow(() -> new RuntimeException("event not found"));
        model.addAttribute("event", event);
        return "redirect:/events";
    }

}
