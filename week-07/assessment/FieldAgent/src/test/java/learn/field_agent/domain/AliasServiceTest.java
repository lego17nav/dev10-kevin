package learn.field_agent.domain;

import learn.field_agent.data.AliasJdbcRepository;
import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Alias;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasServiceTest {

    @Autowired
    AliasService service;

    @MockBean
    AliasRepository repository;

    @Test
    void shouldAdd() {
        Alias alias = makeAlias();
        Alias out = makeAlias();
        out.setAgentId(1);
        when(repository.add(alias)).thenReturn(out);
        Result<Alias> result = service.add(alias);
        assertEquals(ResultType.SUCCESS,result.getType());
    }
    @Test
    void shouldNotAddDuplicatePersonaAndName() {
        Alias alias = makeAlias();
        alias.setName("James");
        alias.setPersona("007");
        when(repository.findAll()).thenReturn(List.of(
                new Alias(1,"James","007",1)
        ));
        Result<Alias> result = service.add(alias);
        assertEquals(ResultType.INVALID,result.getType());
    }

    @Test
    void shouldNotAddBlankName() {
        Alias alias = makeAlias();
        alias.setName("");
        Result<Alias> result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddBlankAgentID() {
        Alias alias = makeAlias();
        alias.setAgentId(0);
        Result<Alias> result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }
    @Test
    void shouldNotAddBlankPersonaIfAliasNotUnique() {
        Alias alias = makeAlias();
        alias.setName("James");
        alias.setPersona("");
        when(repository.findAll()).thenReturn(List.of(
                new Alias(1,"James","Bond",1)
        ));
        Result<Alias> result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }
    @Test
    void shouldNotAddIfIDExist() {
        Alias alias = makeAlias();
        alias.setAliasId(1);
        Result<Alias> result = service.add(alias);
        assertEquals(ResultType.INVALID,result.getType());
    }

    @Test
    void shouldNotUpdateIfNameIsBlank(){
        Alias alias = makeAlias();
        alias.setAliasId(1);
        alias.setName("");
        Result<Alias> result = service.update(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldFindAll() {
        when(repository.findAll()).thenReturn(List.of(
                new Alias(1,"Jack","Mike",1),
                new Alias(2,"Jamie","007",2)
        ));
        assertEquals(2,service.findAll().size());
    }

    @Test
    void shouldNotUpdateIfAliasIdBlank(){
        Alias alias = makeAlias();
        alias.setAliasId(0);
        alias.setName("James");
        Result<Alias> result = service.update(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }
    @Test
    void shouldNotUpdateNoneExistingId() {
        Alias alias = makeAlias();
        alias.setAliasId(2);
        when(repository.findAll()).thenReturn(List.of(
                new Alias(1,"Jack","Bauer",1)
        ));
        Result<Alias> result = service.update(alias);
        assertEquals(ResultType.NOT_FOUND,result.getType());
    }
    @Test
    void shouldNotUpdateDuplicatePersona() {
        Alias alias = makeAlias();
        alias.setAliasId(2);
        alias.setName("Jack");
        alias.setPersona("Bauer");
        when(repository.findAll()).thenReturn(List.of(
                new Alias(1,"Jack","Bauer",1)
        ));
        Result<Alias> result = service.update(alias);
        assertEquals(ResultType.INVALID,result.getType());
    }

    @Test
    void shouldNotDeleteMissing() {
        when(repository.delete(1)).thenReturn(false);
        assertFalse(service.delete(1));
    }

    @Test
    void shouldDelete() {
        when(repository.delete(1)).thenReturn(true);
        assertTrue(service.delete(1));
    }

    @Test
    void shouldNotUpdateBlankPersonaIfAliasNotUnique() {
        Alias alias = makeAlias();
        alias.setName("James");
        alias.setPersona("");
        when(repository.findAll()).thenReturn(List.of(
                new Alias(1,"James","Bond",1)
        ));
        Result<Alias> result = service.update(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateBlankName() {
        Alias alias = makeAlias();
        alias.setName("");
        Result<Alias> result = service.update(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    private Alias makeAlias() {
        Alias alias = new Alias();
        alias.setName("Jack");
        alias.setPersona("Brown");
        alias.setAgentId(1);
        return alias;
    }
}