package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityClearanceService {

    private final SecurityClearanceRepository repository;

    public SecurityClearanceService(SecurityClearanceRepository repository) {this.repository = repository;}

    public List<SecurityClearance> findAll() {return repository.findAll();}

    public SecurityClearance findById(int id) {return repository.findById(id);}

    public boolean delete(int id) {return repository.delete(id);}

    public Result<SecurityClearance> add(SecurityClearance sc) {
        Result<SecurityClearance> result = validate(sc);

        if(!result.isSuccess()) {
            return result;
        }
        if(sc.getSecurityClearanceId() > 0) {
            result.addMessage("Security ID must not be set for add method", ResultType.INVALID);
            return result;
        }
        sc = repository.add(sc);
        result.setPayload(sc);
        return result;
    }

    public Result<SecurityClearance> update(SecurityClearance sc) {
        Result<SecurityClearance> result = validate(sc);

        if(!result.isSuccess()) {
            return result;
        }
        if(sc.getSecurityClearanceId() <= 0) {
            result.addMessage("Security ID must be set for update", ResultType.INVALID);
            return result;
        }
        if(!repository.update(sc)) {
            result.addMessage(String.format("Unable to find Id:%s", sc.getSecurityClearanceId()), ResultType.NOT_FOUND);
        }
        sc = repository.add(sc);
        result.setPayload(sc);
        return result;
    }

    public Result<SecurityClearance> validate(SecurityClearance sc) {
        Result<SecurityClearance> result = new Result<>();

        if(sc.getName() == null || sc.getName().isBlank()) {
            result.addMessage("Name must not be blank", ResultType.INVALID);
        }

        boolean notUnique = findAll().stream().anyMatch(s -> s.getName().equalsIgnoreCase(sc.getName()));

        if(notUnique) {
            result.addMessage("Name is not unique", ResultType.INVALID);
        }

        return result;
    }
}
