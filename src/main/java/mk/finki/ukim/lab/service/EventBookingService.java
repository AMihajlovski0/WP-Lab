package mk.finki.ukim.lab.service;

import mk.finki.ukim.lab.model.EventBooking;

public interface EventBookingService{
    EventBooking placeBooking(String eventName, String username, int numberOfTickets);
}