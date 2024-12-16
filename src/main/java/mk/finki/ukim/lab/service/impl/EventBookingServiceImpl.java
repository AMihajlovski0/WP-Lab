package mk.finki.ukim.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.lab.model.EventBooking;
import mk.finki.ukim.lab.repository.EventBookingRepository;
import mk.finki.ukim.lab.repository.UserRepository;
import mk.finki.ukim.lab.service.EventBookingService;
import org.springframework.stereotype.Component;

@Component
public class EventBookingServiceImpl implements EventBookingService {
    private final EventBookingRepository repo;
    private final UserRepository userRepo;

    public EventBookingServiceImpl(EventBookingRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public EventBooking placeBooking(String eventName, String username, int numberOfTickets) {
        var booking = new EventBooking(eventName, userRepo.findById(username).get(), numberOfTickets);
        return repo.save(booking);
    }
}
