package webzi.basics;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UserControler {
    private final UserRepository userRepository;

    public UserControler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @GetMapping("/neUser")
    public String newUser(@RequestParam(required = false) String type, Model model) {
        model.addAttribute("type", type);
        return "newUser";
    }
    
    @GetMapping("/newUser")
    public String createUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String type) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setType(UserType.valueOf(type));
        userRepository.save(user);
        return "redirect:/login";
    }
}
