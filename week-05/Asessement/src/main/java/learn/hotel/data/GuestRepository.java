package learn.hotel.data;

import learn.hotel.models.Guest;

import java.util.List;

public interface GuestRepository {

    Guest findByID(int id);

    List<Guest> findAll();

    Guest add(Guest guest) throws DataException;
}
