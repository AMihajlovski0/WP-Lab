package mk.finki.ukim.lab.bookstrap;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.model.Location;
import mk.finki.ukim.lab.model.User;
import mk.finki.ukim.lab.repository.EventRepository;
import mk.finki.ukim.lab.repository.LocationRepository;
import mk.finki.ukim.lab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public final static List<Event> events = new ArrayList<>();
    public final static List<Location> locations = new ArrayList<>();
    public final static List<User> users = new ArrayList<>();

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(EventRepository eventRepository, LocationRepository locationRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void init() {
        locations.add(
                new Location(1L, "Arena Toshe Proeski",
                        "Stadion Filip Vtori, Leninova, Skopje, North Macedonia",
                        "33.011 seats",
                        "Stadium in Skopje")
        );
        locations.add(
                new Location(2L, "Sydney Opera House Concert Hall", "Bennelong Point, Sydney Opera " +
                        "House, Sydney, " +
                        "Australia", "2,679 " +
                        "seats",
                        "The biggest opera house in Australia"));

        if (locationRepository.count() == 0) {
            locationRepository.saveAll(locations);
        }

        var arenaSkopje = locations.get(0);
        var arenaSydney = locations.get(1);

        events.add(new Event("Ed Sheeran Concert", "1.1.2025", 4.0, arenaSydney));
        events.add(new Event("Kaliopi Concert", "1.1.2025", 3.0, arenaSkopje));
        events.add(new Event("Bruno Mars Concert", "1.1.2025", 2.0, arenaSydney));
        events.add(new Event("Sasha Matich Concert", "1.1.2025", 4.1, arenaSkopje));
        events.add(new Event("Laufy Concert", "1.1.2025", 4.6, arenaSydney));
        events.add(new Event("Elena Ristova Concert", "1.1.2025", 2.3, arenaSkopje));
        events.add(new Event("Toshe Proeski Tribute Concert", "1.1.2025", 1.9, arenaSkopje));
        events.add(new Event("Macedonian Philharmony", "1.1.2025", 2.7, arenaSkopje));
        events.add(new Event("Jimmy Carr Comedy Show", "1.1.2025", 5.0, arenaSkopje));
        events.add(new Event("Jimmy Carr Comedy Show", "1.1.2025", 2.9, arenaSydney));

        if (eventRepository.count() == 0) {
            eventRepository.saveAll(events);
        }

        users.add(new User("Adrian Mihajlovski", "adrian", "123", passwordEncoder.encode("adrian"), User.Role.User));
        users.add(new User("Admin", "admin", "Admin", passwordEncoder.encode("admin"), User.Role.Admin));

        if (userRepository.count() == 0) {
            userRepository.saveAll(users);
        }
    }
}
