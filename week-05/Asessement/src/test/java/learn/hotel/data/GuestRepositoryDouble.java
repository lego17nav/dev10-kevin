package learn.hotel.data;

import learn.hotel.models.Guest;
import learn.hotel.models.States;

import java.util.ArrayList;
import java.util.List;

public class GuestRepositoryDouble implements GuestRepository {

    public final static Guest guest = makeGuest();

    private final ArrayList<Guest> guests = new ArrayList<>();

    public GuestRepositoryDouble() {guests.add(guest);}

    @Override
    public Guest findByID(int id) {
        return guests.stream()
                .filter(g -> g.getGuestId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Guest> findAll() {
        return guests;
    }

    @Override
    public Guest add(Guest guest) throws DataException {
        return null;
    }

    private static Guest makeGuest() {
        Guest guest = new Guest();
        guest.setGuestId(1);
        guest.setState(States.AK);
        guest.setEmail("Johndoe@gmail.com");
        guest.setFirstName("John");
        guest.setLastName("Doe");
        return guest;
    }
}
