package utm.db.dbadministrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utm.db.dbadministrator.repositories.GradesRepository;

@Service
public class GradesService {

    private final GradesRepository gradesRepository;

    @Autowired
    public GradesService(GradesRepository gradesRepository) {
        this.gradesRepository = gradesRepository;
    }

    public GradesRepository getGradesRepository() {
        return gradesRepository;
    }
}
