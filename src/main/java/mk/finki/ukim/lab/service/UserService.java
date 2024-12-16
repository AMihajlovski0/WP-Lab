package mk.finki.ukim.lab.service;

import mk.finki.ukim.lab.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;
    void registerUser(String name, String username, String address, String password, String repeatedPassword, User.Role role);
}
