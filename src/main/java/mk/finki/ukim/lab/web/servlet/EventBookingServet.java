package mk.finki.ukim.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.lab.service.EventBookingService;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@Profile("servlets")
public class EventBookingServet extends HttpServlet {
    private final SpringTemplateEngine engine;
    private final EventBookingService bookingService;

    public EventBookingServet(SpringTemplateEngine engine, EventBookingService bookingService) {
        this.engine = engine;
        this.bookingService = bookingService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        var eventName = req.getParameter("eventName");
        var numberOFTickets = req.getParameter("numTickets");

        context.setVariable("name", req.getSession().getAttribute("name"));
        context.setVariable("address", req.getSession().getAttribute("address"));
        context.setVariable("event", eventName);
        context.setVariable("count", numberOFTickets);

        engine.process("bookingConfirmation", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var eventName = req.getParameter("event");
        if (eventName != null) try {
            var numberOfTickets = Integer.parseInt(req.getParameter("count"));
            String username = req.getRemoteUser();
            bookingService.placeBooking(eventName, username, numberOfTickets);
        } catch (NumberFormatException ignore) {

        }

        resp.sendRedirect("/");
    }
}
