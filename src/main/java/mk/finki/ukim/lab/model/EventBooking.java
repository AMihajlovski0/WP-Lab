package mk.finki.ukim.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EventBooking {
    @Id
    @GeneratedValue
    private Long id;
    private String eventName;
    @ManyToOne
    private User user;
    private long numberOfTickets;

    public EventBooking(String eventName, User user, int numberOfTickets) {
        this.eventName = eventName;
        this.user = user;
        this.numberOfTickets = numberOfTickets;
    }
}
