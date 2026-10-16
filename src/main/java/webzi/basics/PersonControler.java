package webzi.basics;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PersonControler {
    private final PersonRepository personRepository;
    
    public PersonControler(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    @GetMapping("/newPerson")
    public String newPerson() {
        return "newPerson";
    }
    
    @PostMapping("/newPerson")
    public String addPerson(@RequestParam String first_name,
                            @RequestParam String last_name,
                            @RequestParam String gender) {
        Person person = new Person();
        person.setFirstName(first_name);
        person.setLastName(last_name);
        person.setGender(Gender.valueOf(gender));
        personRepository.save(person);
        return "redirect:/admin";
    }
    
    @GetMapping("/browsePeople")
    public String browsePeople(Model model) {
        List<Person> people = personRepository.findAll();
        model.addAttribute("people", people);
        return "browsePeople";
    }
}
