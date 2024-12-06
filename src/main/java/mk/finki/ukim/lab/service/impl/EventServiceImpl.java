package mk.finki.ukim.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.model.Review;
import mk.finki.ukim.lab.repository.EventRepository;
import mk.finki.ukim.lab.repository.ReviewRepository;
import mk.finki.ukim.lab.service.EventService;
import mk.finki.ukim.lab.service.LocationService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EventServiceImpl implements EventService {
    private final EventRepository repo;
    private final ReviewRepository reviewRepo;
    private final LocationService locationService;

    public EventServiceImpl(EventRepository repo, ReviewRepository reviewRepo, LocationService locationService) {
        this.repo = repo;
        this.reviewRepo = reviewRepo;
        this.locationService = locationService;
    }

    @Override
    @Transactional
    public List<Event> searchEvents(String text, String location, double minRating) {
        return repo.searchEvents(text, location, minRating);
    }

    @Override
    @Transactional
    public Optional<Event> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    @Transactional
    public Optional<Event> addEvent(String name, String description, Long locationId, double rating) {
        return locationService.getById(locationId).map(location ->
                repo.save(new Event(name, description, rating, location))
        );
    }

    @Override
    @Transactional
    public Optional<Event> updateEvent(Long id, String name, String description, Long locationId, double rating) {
        return locationService.getById(locationId).map(location -> {
            repo.deleteById(id);
            return repo.save(new Event(id, name, description, rating, location));
        });
    }

    @Override
    @Transactional
    public void addReview(Long id, String name, Double rating) {
        repo.findById(id).map(
                event -> reviewRepo.save(new Review(name, rating, event))
        );
    }

    @Override
    @Transactional
    public void deleteEvent(long id) {
        repo.deleteById(id);
    }
}
