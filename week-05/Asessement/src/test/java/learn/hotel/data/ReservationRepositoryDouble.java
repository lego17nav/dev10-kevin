package learn.hotel.data;

import learn.hotel.models.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationRepositoryDouble implements ReservationRepository{

    final String hostId = "abcdef1234";

    private final ArrayList<Reservation> reservations = new ArrayList<>();

    public ReservationRepositoryDouble() {
        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setStartDate(LocalDate.of(2022,01,01));
        reservation.setStartDate(LocalDate.of(2022,02,01));
        reservation.setHostId(hostId);
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
        List<Reservation> all = findAll();

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