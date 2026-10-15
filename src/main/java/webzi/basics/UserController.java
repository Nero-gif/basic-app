package webzi.basics;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @GetMapping("/newUser")
    public String newUser(@RequestParam(required = false) String type, Model model) {
        model.addAttribute("type", type);
        return "newUser";
    }
    
    @PostMapping("/newUser")
    public String createUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String type) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setType(UserType.valueOf(type));
        userRepository.save(user);
        if (Objects.equals(type, "ADMIN")) return "redirect:/admin";
        else return "redirect:/login";
    }
}
