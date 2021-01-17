package learn.hotel.domain;

import learn.hotel.data.DataException;
import learn.hotel.data.GuestRepositoryDouble;
import learn.hotel.data.HostRepositoryDouble;
import learn.hotel.data.ReservationRepositoryDouble;
import learn.hotel.models.Guest;
import learn.hotel.models.Host;
import learn.hotel.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    ReservationService service = new ReservationService(new GuestRepositoryDouble(),
            new HostRepositoryDouble(),
            new ReservationRepositoryDouble());
    Host host = new Host();
    Guest guest = new Guest();

    @BeforeEach
    void updateHost() {
        host.setLastName("Jin");
        host.setRegRate(new BigDecimal(100));
        host.setWeekERate(new BigDecimal(150));
        host.setId("abcdef12345");
    }
    @BeforeEach
    void updateGuest() {
        guest.setGuestId(2);
        guest.setLastName("Doe");
    }
    @Test
    void shouldAdd() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHostId("abcdef12345");
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setStartDate(LocalDate.of(2024,01,01));
        reservation.setEndDate(LocalDate.of(2025,01,10));

        Result<Reservation> result = service.add(reservation);
        assertTrue(result.isSuccess());

    }
    @Test
    void shouldNotAddNullGuest() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHostId("abcdef12345");
        reservation.setHost(host);
        reservation.setStartDate(LocalDate.of(2024,01,01));
        reservation.setEndDate(LocalDate.of(2025,01,10));

        Result<Reservation> result = service.add(reservation);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddNullDates() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHostId("abcdef12345");
        reservation.setHost(host);
        reservation.setStartDate(null);
        reservation.setEndDate(LocalDate.of(2025,01,10));

        Result<Reservation> result = service.add(reservation);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddEndDateBeforeStartDate() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHostId("abcdef12345");
        reservation.setHost(host);
        reservation.setStartDate(LocalDate.of(2026,01,01));
        reservation.setEndDate(LocalDate.of(2025,01,10));

        Result<Reservation> result = service.add(reservation);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldDelete() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHostId("abcdef12345");
        reservation.setReservationId(1);
        service.deleteReservation(reservation);

        assertEquals(0,service.findById("reservation-testId").size());
    }

    @Test
    void shouldUpdate() throws  DataException {
        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setHostId("abcdef12345");
        reservation.setHost(host);
        reservation.setGuest(new Guest());
        reservation.setStartDate(LocalDate.of(2026,01,01));
        reservation.setEndDate(LocalDate.of(2027,01,10));
        Result<Reservation> results = service.update(reservation);
        assertTrue(results.isSuccess());
    }

}