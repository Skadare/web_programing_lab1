package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.EventBooking;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="EventBooking", urlPatterns = "/eventbooking")
public class EventBookingServlet  extends HttpServlet {

    final SpringTemplateEngine springTemplateEngine;

    public EventBookingServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        String eventName = req.getParameter("eventName");
        String attendeeName = req.getParameter("username");
        String attendeeAddress = req.getParameter("userAddress");
        String userIP = req.getRemoteAddr();
        Long numTickets = Long.parseLong(req.getParameter("numTickets"));
        EventBooking booking = new EventBooking(eventName, attendeeName, attendeeAddress, numTickets);
        WebContext context = new WebContext(webExchange);
        context.setVariable("booking", booking);
        context.setVariable("clientIp", userIP);
        springTemplateEngine.process("bookingConfirmation.html", context,resp.getWriter());

    }
}
