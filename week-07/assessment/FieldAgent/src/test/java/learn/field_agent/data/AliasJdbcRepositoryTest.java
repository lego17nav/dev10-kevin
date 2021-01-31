package learn.field_agent.data;

import learn.field_agent.models.Alias;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasJdbcRepositoryTest {

    @Autowired
    AliasJdbcRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set(); }


    @Test
    void shouldAdd() {
        Alias alias = createAlias();
        assertNotNull(repository.add(alias));
    }

    @Test
    void shouldDelete() {
        Alias alias = createAlias();
        assertTrue(repository.delete(1));
    }

    @Test
    void shouldNotDeleteNoneExistingId() {
        assertFalse(repository.delete(10));
    }

    @Test
    void shouldUpdate(){
        Alias alias = createAlias();
        repository.add(alias);
        alias.setName("001");
        assertTrue(repository.update(alias));
    }

    @Test
    void shouldNotUpdateMissingAlias() {
       Alias alias = createAlias();
       assertFalse(repository.update(alias));
    }


    private Alias createAlias() {
        Alias alias = new Alias();
        alias.setName("Brando");
        alias.setPersona("007");
        alias.setAgentId(6);
        return alias;
    }

}