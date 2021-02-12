package learn.field_agent.data;

import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceJdbcTemplateRepositoryTest {

    @Autowired
    SecurityClearanceJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        SecurityClearance secret = new SecurityClearance(1, "Secret");
        SecurityClearance topSecret = new SecurityClearance(2, "Top Secret");

        SecurityClearance actual = repository.findById(1);
        assertEquals(secret, actual);

        actual = repository.findById(2);
        assertEquals(topSecret, actual);

        actual = repository.findById(3);
        assertEquals(null, actual);
    }
    @Test
    void shouldFindAll() {
        List<SecurityClearance> all = repository.findAll();
        assertEquals(2,all.size());
    }

    @Test
    void shouldAdd() {
        SecurityClearance sc = new SecurityClearance();
        sc.setName("Super Clearance");
        SecurityClearance out = repository.add(sc);
        assertEquals(3,out.getSecurityClearanceId());
    }

    @Test
    void shouldDelete() {
        SecurityClearance sc = new SecurityClearance();
        sc.setName("Omega Clearance");
        repository.add(sc);
        assertTrue(repository.delete(3));
    }

    @Test
    void shouldNotDeleteNotExistingId() {
        assertFalse(repository.delete(1000));
    }

    @Test
    void shouldUpdate() {
        SecurityClearance sc = new SecurityClearance();
        sc.setSecurityClearanceId(1);
        sc.setName("SuperNova Clearance");
        assertTrue(repository.update(sc));
        SecurityClearance out = repository.findById(1);
        assertEquals("SuperNova Clearance",out.getName());
    }

    @Test
    void shouldNotUpdateNoneExistingId() {
        SecurityClearance sc = new SecurityClearance();
        sc.setSecurityClearanceId(1000);
        sc.setName("SuperNova Clearance");
        assertTrue(repository.update(sc));
    }

}