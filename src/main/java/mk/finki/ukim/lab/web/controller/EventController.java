package mk.finki.ukim.lab.web.controller;

import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.service.EventService;
import mk.finki.ukim.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(
            @RequestParam(name = "event-name", required = false) String eventName,
            @RequestParam(name = "location", required = false) String location,
            @RequestParam(name = "min-rating", required = false) String minRatingParam,
            Model model
    ) {
        if (eventName == null) eventName = "";
        if (location == null) location = "";

        double minRating = 1.0;

        if (minRatingParam != null) try {
            minRating = Double.parseDouble(minRatingParam);
        } catch (NumberFormatException ignored) {

        }

        model.addAttribute("events", eventService.searchEvents(eventName, location, minRating));
        model.addAttribute("eventName", eventName);
        model.addAttribute("minRating", minRating);
        model.addAttribute("location", location);

        return "listEvents.html";
    }

    @PostMapping("/add")
    public String saveEvent(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("popularityScore") Double popularityScore,
            @RequestParam("location") Long location
    ) {
        eventService.addEvent(name, description, location, popularityScore);
        return "redirect:/events";
    }

    @PostMapping("/edit/{id}")
    public String editEvent(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("popularityScore") Double popularityScore,
            @RequestParam("location") Long location
    ) {
        eventService.updateEvent(id, name, description, location, popularityScore);
        return "redirect:/events";
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }

    @GetMapping("edit-form/{id}")
    public String getEditEventForm(@PathVariable Long id, Model model) {
        Optional<Event> maybeEvent = eventService.getById(id);

        if (maybeEvent.isEmpty()) {
            return "redirect:/events";
        }

        Event event = maybeEvent.get();
        model.addAttribute("event", event);
        model.addAttribute("locations", locationService.listAll());

        return "add-event.html";
    }

    @GetMapping("add-form")
    public String getAddEventForm(Model model) {
        model.addAttribute("locations", locationService.listAll());
        return "add-event.html";
    }

    @GetMapping("review-form/{id}")
    public String getReviewEventForm(@PathVariable Long id, Model model) {
        Optional<Event> maybeEvent = eventService.getById(id);

        if (maybeEvent.isEmpty()) {
            return "redirect:/events";
        }

        model.addAttribute("event", maybeEvent.get());
        return "add-review.html";
    }

    @PostMapping("review/{id}")
    public String reviewEventForm(
            @PathVariable Long id,
            @RequestParam("text") String text,
            @RequestParam("rating") Double rating,
            Model model
    ) {
        eventService.addReview(id, text, rating);

        return "redirect:/events";
    }
}
