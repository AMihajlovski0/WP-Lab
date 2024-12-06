package mk.finki.ukim.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.lab.model.EventBooking;
import mk.finki.ukim.lab.repository.EventBookingRepository;
import mk.finki.ukim.lab.service.EventBookingService;
import org.springframework.stereotype.Component;

@Component
public class EventBookingServiceImpl implements EventBookingService {
    private final EventBookingRepository repo;

    public EventBookingServiceImpl(EventBookingRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        var booking = new EventBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);
        return repo.save(booking);
    }
}
