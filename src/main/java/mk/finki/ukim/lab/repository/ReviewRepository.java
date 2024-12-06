package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
