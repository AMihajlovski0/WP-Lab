package mk.finki.ukim.lab.service;

import mk.finki.ukim.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> listAll();
    Optional<Location> getById(Long id);
}
