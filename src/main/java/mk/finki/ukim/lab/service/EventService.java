package mk.finki.ukim.lab.service;

import mk.finki.ukim.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> searchEvents(String text, String location, double minRating);
    Optional<Event> getById(Long id);
    Optional<Event> addEvent(String name, String description, Long locationId, double rating);
    Optional<Event> updateEvent(Long id, String name, String description, Long locationId, double rating);
    void addReview(Long id, String name, Double rating);
    void deleteEvent(long id);
}
