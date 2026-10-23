package webzi.basics;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    boolean existsByPersonAAndPersonB(Person personA, Person personB);
}
