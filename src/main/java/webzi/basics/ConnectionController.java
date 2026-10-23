package webzi.basics;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ConnectionController {
    private final ConnectionRepository connectionRepository;
    private final PersonRepository personRepository;

    public ConnectionController(ConnectionRepository connectionRepository, PersonRepository personRepository) {
        this.connectionRepository = connectionRepository;
        this.personRepository = personRepository;
    }

    @GetMapping("/newConnection")
    public String newConnection(Model model) {
        model.addAttribute("people", personRepository.findAll());
        return "newConnection";
    }

    @PostMapping("/newConnection")
    public String addConnection(@RequestParam Long person_a,
                                @RequestParam Long person_b,
                                Model model) {
        if (person_a.equals(person_b)) {
            return showError(model, "This connection is not allowed");
        }
        
        Person personA = personRepository.findById(Math.min(person_a, person_b)).orElse(null);
        Person personB = personRepository.findById(Math.max(person_a, person_b)).orElse(null);
        if (personA == null || personB == null) {
            return showError(model, "Person does not exist");
        }
        if (connectionRepository.existsByPersonAAndPersonB(personA, personB)) {
            return showError(model, "This connection already exists");
        }

        Connection connection = new Connection();
        connection.setPersonA(personA);
        connection.setPersonB(personB);
        connectionRepository.save(connection);
        return "redirect:/map";
    }

    @GetMapping("/map")
    public String map(Model model) {
        List<Map<String, Object>> nodes = personRepository.findAll().stream()
                .map(p -> Map.<String, Object>of(
                        "id", p.getId(),
                        "label", p.getFirstName() + " " + p.getLastName(),
                        "group", p.getGender().name(),
                        "firstName", p.getFirstName(),
                        "lastName", p.getLastName()))
                .toList();
        List<Map<String, Object>> edges = connectionRepository.findAll().stream()
                .map(c -> Map.<String, Object>of(
                        "from", c.getPersonA().getId(),
                        "to", c.getPersonB().getId()))
                .toList();
        model.addAttribute("nodes", nodes);
        model.addAttribute("edges", edges);
        return "map";
    }

    private String showError(Model model, String error) {
        model.addAttribute("people", personRepository.findAll());
        model.addAttribute("error", error);
        return "newConnection";
    }
}
