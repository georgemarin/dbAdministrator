package utm.db.dbadministrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utm.db.dbadministrator.repositories.CreditsRepository;

@Service
public class CreditsService {

    private final CreditsRepository creditsRepository;

    @Autowired
    public CreditsService(CreditsRepository creditsRepository) {
        this.creditsRepository = creditsRepository;
    }

    public CreditsRepository getCreditsRepository() {
        return creditsRepository;
    }
}
