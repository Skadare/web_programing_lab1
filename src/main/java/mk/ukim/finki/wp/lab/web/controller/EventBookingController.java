package mk.ukim.finki.wp.lab.web.controller;


import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {

    private final EventBookingService eventBookingService;

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }
    @GetMapping
    public String getBookingPage(Model model) {
        return "bookingConfirmation";
    }
    @PostMapping
    public String addBooking(@RequestParam String eventName, @RequestParam String username, @RequestParam String userAddress, @RequestParam String numTickets, HttpServletRequest request, Model model)
    {
        String clientIp =request.getRemoteAddr();
        model.addAttribute("clientIp", clientIp);
        EventBooking booking = eventBookingService.placeBooking(eventName, username, userAddress, Integer.parseInt(numTickets));
        model.addAttribute("booking", booking);
        return "bookingConfirmation";

    }
}
