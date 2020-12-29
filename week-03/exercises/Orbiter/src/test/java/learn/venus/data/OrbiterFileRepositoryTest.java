package learn.venus.data;

import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrbiterFileRepositoryTest {

    private static final String SEED_PATH = "./data/orbiter-seed.csv";
    private static final String TEST_PATH = "./data/orbiter-test.csv";

    private OrbiterFileRepository repository = new OrbiterFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Files.copy(Paths.get(SEED_PATH), Paths.get(TEST_PATH), StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindFiveOrbiters() {
        List<Orbiter> actual = repository.findAll();
        assertNotNull(actual);

        assertEquals(5,actual.size());
    }

    @Test
    void shouldFindExistingOrbiter() {
        Orbiter celestyn = repository.findById(4);
    }

    @Test
    void shouldAddOrbiter() throws DataAccessException {
        Orbiter orbiter = new Orbiter();
        orbiter.setType(OrbiterType.MODULE);
        orbiter.setName("Test Nodule");
        orbiter.setSponsor("Test Sponsor");

        Orbiter actual = repository.add(orbiter);

        assertNotNull(actual);
        assertEquals(6,actual.getOrbiterId());
    }
}