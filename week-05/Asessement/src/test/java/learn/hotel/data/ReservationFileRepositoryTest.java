package learn.hotel.data;

import static org.junit.jupiter.api.Assertions.*;
import learn.hotel.models.Host;
import learn.hotel.models.Reservation;
import learn.hotel.data.ReservationFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

class ReservationFileRepositoryTest {
    static final String SEED_PATH = "./data/Test_Data/reservation-seedId.csv";
    static final String TEST_PATH = "./data/Test_Data/reservation-testId.csv";
    static final String TEST_DIR_PATH = "./data/Test_Data";

    ReservationFileRepository reservationFileRepository = new ReservationFileRepository(TEST_DIR_PATH);
    Host testHost = new Host();
    Reservation reservationTest = new Reservation();

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_PATH);
        Path testPath = Paths.get(TEST_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindId() {
        List<Reservation> reservations = reservationFileRepository.findById("reservation-testId");
        assertEquals(13,reservations.size());
    }

    void shouldAdd() {
        List<Reservation> reservations = reservationFileRepository.findById("reservation-testId");
        Reservation testReservation = new Reservation();
        reservations.add(testReservation);
        assertEquals(14,reservations.size());
    }
}