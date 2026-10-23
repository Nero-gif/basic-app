package webzi.basics;

import jakarta.persistence.*;

@Entity
@Table(name = "connections")
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "person_a_id", nullable = false)
    private Person personA;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_b_id", nullable = false)
    private Person personB;


    public Long getId() { return id; }

    public Person getPersonA() { return personA; }
    public void setPersonA(Person personA) { this.personA = personA; }

    public Person getPersonB() { return personB; }
    public void setPersonB(Person personB) { this.personB = personB; }

}
