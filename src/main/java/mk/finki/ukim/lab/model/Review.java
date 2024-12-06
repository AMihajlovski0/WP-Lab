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
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private double rating;
    @ManyToOne
    private Event of;

    public Review(String text, double rating, Event of) {
        this.text = text;
        this.rating = rating;
        this.of = of;
    }
}
