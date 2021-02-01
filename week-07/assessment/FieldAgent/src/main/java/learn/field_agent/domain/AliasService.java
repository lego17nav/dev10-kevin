package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Alias;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AliasService {


    private final AliasRepository repository;

    public AliasService(AliasRepository repository) {
        this.repository = repository;
    }

    public boolean delete(int id) {return repository.delete(id);}

    public List<Alias> findAll() {return repository.findAll();}

    public Result<Alias> add(Alias alias) {
        Result<Alias> result = validate(alias);

        if(!result.isSuccess()) {
            return result;
        }
        if(alias.getAliasId() > 0) {
            result.addMessage("Add must not have ID set", ResultType.INVALID);
        }
        alias = repository.add(alias);
        result.setPayload(alias);
        return result;
    }

    public Result<Alias> update(Alias alias) {
        Result<Alias> result = validate(alias);

        if(!result.isSuccess()) {
            return result;
        }

        if(alias.getAliasId() == 0) {
            result.addMessage("You need an ID to update", ResultType.INVALID);
            return result;
        }
        if(!repository.update(alias)) {
            result.addMessage(String.format("Unable to find id: %s", alias.getAliasId()),ResultType.NOT_FOUND);
        }

        return result;
    }


    private Result<Alias> validate(Alias alias) {

        Result<Alias> result = new Result<>();

        if(alias.getAgentId() == 0) {
            result.addMessage("Need Agent Id",ResultType.INVALID);
        }

        if(alias.getName().isBlank() || alias.getName() == null) {
            result.addMessage("Need agent alias", ResultType.INVALID);
        }

        if(!(alias.getPersona().isBlank() && alias.getName().isBlank())) {
            List<Alias> aliasFilter = findAll().stream().
                    filter(a -> a.getName().equalsIgnoreCase(alias.getName()))
                    .collect(Collectors.toList());
            boolean duplicatePersona = aliasFilter.stream()
                    .anyMatch(fa -> fa.getPersona().equalsIgnoreCase(alias.getPersona()));
            if(duplicatePersona) {
                result.addMessage("Alias is duplicate so Persona must be unique", ResultType.INVALID);
            }
        }

        if(alias.getPersona().isBlank() || alias.getPersona().equals(null)) {
            boolean required = findAll().stream().anyMatch(a -> a.getName().equalsIgnoreCase(alias.getName()));
            if(required) {
                result.addMessage("Alias name isn't unique need a persona", ResultType.INVALID);
            }
        }
        return result;
    }

}
