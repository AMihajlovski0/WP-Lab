package mk.finki.ukim.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.lab.model.User;
import mk.finki.ukim.lab.service.EventBookingService;
import mk.finki.ukim.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/event-booking")
public class EventBookingController {
    private final EventBookingService eventBookingService;
    private final UserService userService;

    public EventBookingController(EventBookingService eventBookingService, UserService userService) {
        this.eventBookingService = eventBookingService;
        this.userService = userService;
    }

    @GetMapping
    public String getEventBookingPage(
            @RequestParam String eventName,
            @RequestParam Integer numTickets,
            HttpServletRequest req,
            Model model) {
        String username = req.getRemoteUser();
        User user = userService.loadUserByUsername(username);
        model.addAttribute("name", user.getName());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("event", eventName);
        model.addAttribute("count", numTickets);

        return "bookingConfirmation.html";
    }

    @PostMapping
    public String makeBookingPage(
            @RequestParam String event,
            @RequestParam Integer count,
            HttpServletRequest req
    ) {
        String username = req.getRemoteUser();
        eventBookingService.placeBooking(event, username, count);

        return "redirect:/events";
    }
}
