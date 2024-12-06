package mk.finki.ukim.lab.web.controller;

import mk.finki.ukim.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/event-booking")
public class EventBookingController {
    private final EventBookingService eventBookingService;

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }


    @GetMapping
    public String getEventBookingPage(
            @RequestParam String eventName,
            @RequestParam Integer numTickets,
            @SessionAttribute String name,
            @SessionAttribute String address,
            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("event", eventName);
        model.addAttribute("count", numTickets);

        return "bookingConfirmation.html";
    }

    @PostMapping
    public String makeBookingPage(
            @RequestParam String event,
            @RequestParam Integer count,
            @SessionAttribute("name") String name,
            @SessionAttribute("address") String address
    ) {
        eventBookingService.placeBooking(event, name, address, count);


        return "redirect:/events";
    }
}
