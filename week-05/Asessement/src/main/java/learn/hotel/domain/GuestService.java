package learn.hotel.domain;

import learn.hotel.data.GuestFileRepository;
import learn.hotel.models.Guest;

import java.util.List;
import java.util.stream.Collectors;

public class GuestService {

    private final GuestFileRepository repository;

    public GuestService(GuestFileRepository guestFileRepository) {this.repository = guestFileRepository;}

    public List<Guest> findByLastName(String prefix) {
        return repository.findAll().stream()
                .filter(g -> g.getLastName().startsWith(prefix))
                .collect(Collectors.toList());
    }

}
