package learn.hotel.data;

import learn.hotel.models.Host;
import learn.hotel.models.Reservation;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public interface ReservationRepository {

    List<Reservation> findById(String id);

    Reservation add(Reservation reservation) throws DataException;

    boolean update(Reservation reservation) throws DataException;

    boolean delete(Reservation reservation) throws DataException;

    List<Reservation> readAllFiles(int guestID) throws DataException, IOException;
}
