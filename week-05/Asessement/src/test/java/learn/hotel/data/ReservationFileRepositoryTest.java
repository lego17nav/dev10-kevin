package learn.hotel.data;

import static org.junit.jupiter.api.Assertions.*;
import learn.hotel.models.Host;
import learn.hotel.models.Reservation;
import learn.hotel.data.ReservationFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
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
        testHost.setId("reservation-TestId");
        testHost.setRegRate(new BigDecimal(100));
        testHost.setWeekERate(new BigDecimal(200));

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindId() {
        List<Reservation> reservations = reservationFileRepository.findById("reservation-testId");
        assertEquals(13,reservations.size());
    }

    @Test
    void shouldAdd() throws DataException {
        reservationTest.setReservationId(1);
        reservationTest.setHostId("reservation-testId");
        reservationTest.setHost(testHost);
        reservationTest.setStartDate(LocalDate.of(2023,01,01));
        reservationTest.setEndDate(LocalDate.of(2023,01,01));
        reservationFileRepository.add(reservationTest);
        List<Reservation> reservations = reservationFileRepository.findById("reservation-testId");
        assertEquals(14,reservations.size());
    }

    @Test
    void shouldUpdate() throws DataException {
        reservationTest.setReservationId(1);
        reservationTest.setHostId("reservation-testId");
        reservationTest.setHost(testHost);
        reservationTest.setStartDate(LocalDate.of(2023,01,01));
        reservationTest.setEndDate(LocalDate.of(2023,01,01));
        assertTrue(reservationFileRepository.update(reservationTest));

    }

    @Test
    void shouldDelete() throws DataException {
        reservationTest.setReservationId(1);
        reservationTest.setHostId("reservation-testId");
        reservationTest.setHost(testHost);
        assertTrue(reservationFileRepository.delete(reservationTest));
    }
}