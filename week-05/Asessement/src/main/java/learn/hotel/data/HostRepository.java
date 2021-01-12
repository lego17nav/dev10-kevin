package learn.hotel.data;

import learn.hotel.models.Host;
import learn.hotel.models.Reservation;

import java.util.List;

public interface HostRepository {

    List<Host> findAll();

    Host findById(String id);

    Host add(Host host) throws DataException;

}
