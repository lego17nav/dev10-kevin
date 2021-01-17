package learn.hotel.domain;

import learn.hotel.data.DataException;
import learn.hotel.data.GuestRepositoryDouble;
import learn.hotel.models.Guest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuestServiceTest {

    GuestService service = new GuestService(new GuestRepositoryDouble());

    Guest guest1 = new Guest();
    Guest guest2 = new Guest();

    @Test
    void shouldReturnAll() throws DataException {
        assertEquals(1,service.findAll().size());
    }

    @Test
    void shouldFindDoe() throws DataException {
        assertEquals(1,service.findByLastName("D").size());
    }
}
