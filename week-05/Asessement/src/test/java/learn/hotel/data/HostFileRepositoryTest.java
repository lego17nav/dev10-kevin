package learn.hotel.data;

import learn.hotel.models.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostFileRepositoryTest {

    static final String SEED_PATH = "./data/Test_Data/host-seed.csv";
    static final String TEST_PATH = "./data/Test_Data/host-test.csv";

    HostFileRepository hostFileRepository = new HostFileRepository(TEST_PATH);

    Host host = new Host();

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_PATH);
        Path testPath = Paths.get(TEST_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() {
        List<Host> all = hostFileRepository.findAll();
        assertEquals(10,all.size());
    }
    @Test
    void shouldAdd() {
        //id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate
        List<Host> all = hostFileRepository.findAll();
        host.setLastName("Doe");
        host.setEmail("Sample@gmail.com");

        all.add(host);
        assertEquals(11,all.size());
    }

    void shouldFindById() {
        Host host = hostFileRepository.findById("3edda6bc-ab95-49a8-8962-d50b53f84b15");
        assertNotNull(host);
    }
}