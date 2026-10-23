package webzi.basics;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/")
    public String form() {
        return "redirect:/login";
    }

    @PostMapping("/greet")
    public String greet(@RequestParam String firstName,
                        @RequestParam String lastName,
                        Model model) {
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        return "form";
    }
}
