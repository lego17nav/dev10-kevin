package learn.hotel.data;

import learn.hotel.models.Guest;
import learn.hotel.models.Host;
import learn.hotel.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestFileRepositoryTest {

    static final String SEED_PATH = "./data/Test_Data/guest-seed.csv";
    static final String TEST_PATH = "./data/Test_Data/guest-test.csv";

    GuestFileRepository guestFileRepository = new GuestFileRepository(TEST_PATH);

    Host host = new Host();

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_PATH);
        Path testPath = Paths.get(TEST_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() {
        List<Guest> guests = guestFileRepository.findAll();
        assertEquals(7,guests.size());
    }
}