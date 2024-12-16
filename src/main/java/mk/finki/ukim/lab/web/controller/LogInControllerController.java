package mk.finki.ukim.lab.web.controller;

import mk.finki.ukim.lab.model.User;
import mk.finki.ukim.lab.repository.UserRepository;
import mk.finki.ukim.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class LogInControllerController {
    private final UserService service;

    public LogInControllerController(UserService service) {
        this.service = service;
    }


    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage() {
        return "access-denied.html";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register.html";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String name,
            @RequestParam String username,
            @RequestParam String address,
            @RequestParam String password,
            @RequestParam("confirm_password") String passwordAgain
    ) {
        try {
            service.registerUser(name, username, address, password, passwordAgain, User.Role.User);
            return "redirect:/login";
        } catch (RuntimeException e) {
            return "redirect:/register";
        }
    }
}
