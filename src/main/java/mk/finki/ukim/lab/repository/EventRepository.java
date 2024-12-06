package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select E from Event E where (E.name like %?1% or E.description like %?1%) and (?2 is null or E.location" +
            ".id " +
            "= ?2) and E.popularityScore >= ?3")
    List<Event> searchEvents(String text, Long locationId, double minRating);
}
