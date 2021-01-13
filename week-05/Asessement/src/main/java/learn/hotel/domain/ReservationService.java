package learn.hotel.domain;

import learn.hotel.data.ReservationFileRepository;
import learn.hotel.data.GuestFileRepository;
import learn.hotel.data.HostFileRepository;
import learn.hotel.models.Guest;
import learn.hotel.models.Host;
import learn.hotel.models.Reservation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationService {

    private final GuestFileRepository guestFileRepository;
    private final HostFileRepository hostFileRepository;
    private final ReservationFileRepository reservationFileRepository;

    public ReservationService(GuestFileRepository guestFileRepository, HostFileRepository hostFileRepository
    , ReservationFileRepository reservationFileRepository) {
        this.guestFileRepository = guestFileRepository;
        this.hostFileRepository = hostFileRepository;
        this.reservationFileRepository = reservationFileRepository;
    }

    public List<Reservation> findById(String id) {
        Map<Integer, Guest> guestMap = guestFileRepository.findAll().stream()
                .collect(Collectors.toMap(g -> g.getGuestId(), g -> g));
        Map<String, Host> hostMap = hostFileRepository.findAll().stream()
                .collect(Collectors.toMap(h -> h.getId(), h -> h));

        List<Reservation> result = reservationFileRepository.findById(id);
        for(Reservation reservation: result) {
            reservation.setGuest(guestMap.get(reservation.getGuest().getGuestId()));
            reservation.setHost(hostMap.get(reservation.getHost().getId()));
        }

        return result;

    }

}
