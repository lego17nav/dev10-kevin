package learn.hotel.data;

import learn.hotel.models.Guest;
import learn.hotel.models.Host;
import learn.hotel.models.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

public class ReservationRepositoryDouble implements ReservationRepository{

    //final String hostId = "Test1";

    private final ArrayList<Reservation> reservations = new ArrayList<>();

    public ReservationRepositoryDouble() {
        Guest guest = new Guest();
        Host host = new Host();
        host.setRegRate(new BigDecimal(100));
        host.setWeekERate(new BigDecimal(150));
        host.setLastName("George");
        host.setId("Test-abc");
        Reservation reservation = new Reservation();
        reservation.setHostId(host.getId());
        reservation.setReservationId(1);
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setStartDate(LocalDate.of(2022,01,01));
        reservation.setEndDate(LocalDate.of(2022,01,05));
        reservations.add(reservation);
    }

    public List<Reservation> findAll() {return reservations;}


    @Override
    public List<Reservation> findById(String id) {
        return reservations.stream()
                .filter(i -> i.getHostId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation add(Reservation reservation) throws DataException {
        List<Reservation> all = findById(reservation.getHostId());

        int nextId = all.stream()
                .mapToInt(Reservation::getReservationId)
                .max()
                .orElse(0) + 1;

        reservation.setReservationId(nextId);

        all.add(reservation);
        return reservation;
    }

    @Override
    public boolean update(Reservation reservation) throws DataException {
        List<Reservation> reservations = findById(reservation.getHostId());
        reservations.stream().forEach(r -> r.setHost(reservation.getHost()));

        for(int i = 0; i < reservations.size(); i++) {
            if(reservations.get(i).getReservationId() == reservation.getReservationId()) {
                reservations.set(i,reservation);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Reservation reservation) {
        List<Reservation> reservations = findById(reservation.getHostId());
        reservations.stream().forEach(r -> r.setHost(reservation.getHost()));

        for(int i = 0; i < reservations.size(); i++) {
            if(reservations.get(i).getReservationId() == reservation.getReservationId()) {
                reservations.remove(i);
                return true;
            }
        }
        return false;
    }
}
