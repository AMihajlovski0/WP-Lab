package mk.finki.ukim.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.lab.service.EventService;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@Profile("servlets")
public class EventListServlet extends HttpServlet {
    private final SpringTemplateEngine engine;
    private final EventService eventService;

    public EventListServlet(SpringTemplateEngine engine, EventService eventService) {
        this.engine = engine;
        this.eventService = eventService;
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var eventName = req.getParameter("event-name");
//        var location = req.getParameter("location");
//        var minRatingParam = req.getParameter("min-rating");
//
//        if (eventName == null) eventName = "";
//
//        if (location == null) location = "";
//
//        double minRating = 1.0;
//
//        if (minRatingParam != null) try {
//            minRating = Double.parseDouble(minRatingParam);
//        } catch (NumberFormatException ignored) {
//
//        }
//
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//        context.setVariable("events", eventService.searchEvents(eventName, location, minRating));
//        context.setVariable("eventName", eventName);
//        context.setVariable("minRating", minRating);
//        context.setVariable("location", location);
//        engine.process("listEvents.html", context, resp.getWriter());
//    }
}
