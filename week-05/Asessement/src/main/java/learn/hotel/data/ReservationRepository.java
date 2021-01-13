package learn.hotel.data;

import learn.hotel.models.Reservation;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> findById(String id);

    Reservation add(Reservation reservation);

    boolean update(Reservation reservation) throws DataException;
}
