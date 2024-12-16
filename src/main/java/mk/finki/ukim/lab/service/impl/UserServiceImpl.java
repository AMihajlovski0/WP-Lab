package mk.finki.ukim.lab.service.impl;

import mk.finki.ukim.lab.model.User;
import mk.finki.ukim.lab.repository.UserRepository;
import mk.finki.ukim.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public void registerUser(String name, String username, String address, String password, String repeatedPassword,
                             User.Role role) {
        repo.save(new User(name, username, address, password, role));
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
