package learn.unexplained.domain;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.data.EncounterRepository;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.List;
import java.util.Objects;

public class EncounterService {

    private final EncounterRepository repository;

    public EncounterService(EncounterRepository repository) {
        this.repository = repository;
    }

    public List<Encounter> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public EncounterResult add(Encounter encounter) throws DataAccessException {
        EncounterResult result = validate(encounter);
        if (!result.isSuccess()) {
            return result;
        }

        // check for duplicate

        encounter = repository.add(encounter);
        result.setPayload(encounter);
        return result;
    }

    private EncounterResult validate(Encounter encounter) throws DataAccessException {

        EncounterResult result = new EncounterResult();
        if (encounter == null) {
            result.addErrorMessage("encounter cannot be null");
            return result;
        }

        if (encounter.getWhen() == null || encounter.getWhen().trim().length() == 0) {
            result.addErrorMessage("when is required");
        }

        if (encounter.getDescription() == null || encounter.getDescription().trim().length() == 0) {
            result.addErrorMessage("description is required");
        }

        if (encounter.getOccurrences() <= 0) {
            result.addErrorMessage("occurrences must be greater than 0");
        }

        List<Encounter> encounters = repository.findAll();
        for (Encounter e : encounters) {
            if (Objects.equals(encounter.getWhen(), e.getWhen())
                    && Objects.equals(encounter.getType(), e.getType())
                    && Objects.equals(encounter.getDescription(), e.getDescription())) {
                result.addErrorMessage("duplicate encounter is not allowed");
                break;
            }
        }

        return result;
    }

    public List<Encounter> findByType(EncounterType encounterType) throws DataAccessException {
        List<Encounter> encounters = findAll();
        for (Encounter encounter: encounters) {
            if(encounterType == encounter.getType()) {
                encounters.add(encounter);
            }
        }
        return encounters;
    }

    public EncounterResult update(Encounter encounter) throws DataAccessException {
        EncounterResult result = validate(encounter);
        if(!result.isSuccess()) {
            return result;
        }

        if(encounter.getEncounterId() <= 0) {
            result.addErrorMessage("Encounter Id is required");
        }

        }

        Encounter existing = repository.up
    }
}
