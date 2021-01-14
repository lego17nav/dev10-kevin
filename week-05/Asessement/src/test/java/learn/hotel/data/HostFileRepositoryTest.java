package learn.hotel.data;

import learn.hotel.models.Host;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostFileRepositoryTest {

    @Test
    void shouldFindAll() {
        HostFileRepository repo = new HostFileRepository("./data/hosts.csv");
        List<Host> all = repo.findAll();
        assertEquals(1000,all.size());
    }
}