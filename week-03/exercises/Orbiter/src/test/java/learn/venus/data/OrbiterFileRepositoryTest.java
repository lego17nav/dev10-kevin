package learn.venus.data;

import learn.venus.models.Orbiter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrbiterFileRepositoryTest {

    private OrbiterFileRepository repository = new OrbiterFileRepository("C:\\Users\\KevinLimlengco\\Source\\dev10-kevin\\week-03\\exercises\\Orbiter\\data\\orbiter.csv");
    @Test
    void shouldFindFiveOrbiters() {
        List<Orbiter> actual = repository.findAll();
        assertNotNull(actual);

        assertEquals(5,actual.size());
    }

    @Test
    void shouldFindExistingOrbiter() {
        Orbiter celestyn = repository.findById(4);
        assertno
    }
}