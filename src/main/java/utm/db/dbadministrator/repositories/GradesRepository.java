package utm.db.dbadministrator.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import utm.db.dbadministrator.entities.Grades;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradesRepository extends CrudRepository<Grades, Long> {

    Optional<Grades> findById(Long id);

    List<Grades> findAll();
}
