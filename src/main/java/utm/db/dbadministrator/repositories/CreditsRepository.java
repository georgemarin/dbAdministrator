package utm.db.dbadministrator.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import utm.db.dbadministrator.entities.Credits;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditsRepository extends CrudRepository<Credits, Long> {

    Optional<Credits> findById(Long id);

    List<Credits> findAll();
}
