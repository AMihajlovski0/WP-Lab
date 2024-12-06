package mk.finki.ukim.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/details")
public class DetailsController {
    @GetMapping
    public String getDetailsPage() {
        return "details.html";
    }

    @PostMapping
    public String sendDetails(
            @RequestParam String name,
            @RequestParam String address,
            HttpSession session
    ) {
        session.setAttribute("name", name);
        session.setAttribute("address", address);

        return "redirect:/events";
    }
}
