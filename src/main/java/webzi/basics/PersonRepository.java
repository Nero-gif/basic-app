package webzi.basics;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE p.firstName = :search OR p.lastName = :search OR CAST(p.gender AS string) = :search")
    List<Person> find(@Param("search") String firstName);
}

