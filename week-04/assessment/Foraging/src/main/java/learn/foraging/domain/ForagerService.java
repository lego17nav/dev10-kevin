package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepository;
import learn.foraging.models.Forager;

import java.util.List;
import java.util.stream.Collectors;

public class ForagerService {

    private final ForagerRepository repository;

    public ForagerService(ForagerRepository repository) {
        this.repository = repository;
    }

    public List<Forager> findByState(String stateAbbr) {
        return repository.findByState(stateAbbr);
    }

    public List<Forager> findAll() {
        return repository.findAll().stream()
                .collect(Collectors.toList());
    }

    public List<Forager> findByLastName(String prefix) {
        return repository.findAll().stream()
                .filter(i -> i.getLastName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    public Result<Forager> add(Forager forager) throws DataException {
        Result<Forager> result = new Result<>();
        if(forager == null) {
            result.addErrorMessage("Forager must not be null");
        }

        if(forager.getFirstName() == null || forager.getFirstName().isBlank()) {
            result.addErrorMessage("First name must not be missing");
        }

        if(forager.getLastName() == null || forager.getLastName().isBlank()) {
            result.addErrorMessage("Last Name must be present");
        }

        if(forager.getState() == null || forager.getState().isBlank()) {
            result.addErrorMessage("State must not be blank");
        }

        if(repository.findAll().stream().anyMatch(f -> f.getIdentifier().equalsIgnoreCase(forager.getIdentifier()))) {
            result.addErrorMessage("This person is already listed in our books");
        }

        if(!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(forager));

        return result;
    }

}
