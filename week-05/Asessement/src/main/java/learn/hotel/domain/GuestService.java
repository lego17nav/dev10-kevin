package learn.hotel.domain;

import learn.hotel.data.GuestFileRepository;
import learn.hotel.data.GuestRepository;
import learn.hotel.models.Guest;

import java.util.List;
import java.util.stream.Collectors;

public class GuestService {

    private final GuestRepository repository;

    public GuestService(GuestRepository guestFileRepository) {this.repository = guestFileRepository;}

    public List<Guest> findByLastName(String prefix) {
        return repository.findAll().stream()
                .filter(g -> g.getLastName().startsWith(prefix))
                .sorted((g1,g2) -> g1.getLastName().compareTo(g2.getLastName()))
                .collect(Collectors.toList());
    }

    public List<Guest> findAll() {
        return repository.findAll();
    }
}
