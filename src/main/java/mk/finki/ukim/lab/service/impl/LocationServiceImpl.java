package mk.finki.ukim.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.lab.model.Location;
import mk.finki.ukim.lab.repository.LocationRepository;
import mk.finki.ukim.lab.service.LocationService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LocationServiceImpl implements LocationService {
    private final LocationRepository repo;

    public LocationServiceImpl(LocationRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public List<Location> listAll() {
        return repo.findAll();
    }

    @Override
    @Transactional
    public Optional<Location> getById(Long id) {
        return repo.findById(id);
    }
}
