package learn.hotel.domain;

import learn.hotel.data.DataException;
import learn.hotel.data.ReservationFileRepository;
import learn.hotel.data.GuestFileRepository;
import learn.hotel.data.HostFileRepository;
import learn.hotel.models.Guest;
import learn.hotel.models.Host;
import learn.hotel.models.Reservation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.cert.CertSelector;
import java.time.DayOfWeek;
import java.time.LocalDate;
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

        for (Reservation reservation : result) {
            reservation.setGuest(guestMap.get(reservation.getGuestId()));
            reservation.setHost(hostMap.get(reservation.getHostId()));
        }
        return result;

    }

    public Result<Reservation> add(Reservation reservation) throws DataException {
        Result<Reservation> result = validate(reservation);
        Host host = reservation.getHost();
        Guest guest = reservation.getGuest();
        if (!result.isSuccess()) {
            return result;
        }

        //reservation.setTotalPrice(calculateTotal(reservation));
        result.setPayload(reservationFileRepository.add(reservation));
        Host host1 = reservation.getHost();

        return result;
    }

    public Result<Reservation> update(Reservation reservation) throws DataException {

        Result<Reservation> result = validate(reservation);

        if (result.isSuccess()) {
            if (reservationFileRepository.update(reservation)) {
                result.setPayload(reservation);
            } else {
                String message = String.format("An error occur while updating the reservation");
            }
        }
        return result;
    }


    public Result<Reservation> validate(Reservation reservation) {

        Result<Reservation> result = validateNulls(reservation);
        if (!result.isSuccess()) {
            return result;
        }
        validateDate(reservation, result);

        return result;
    }

    public Result<Reservation> validateNulls(Reservation reservation) {
        //id,start_date,end_date,guest_id,total
        Result<Reservation> result = new Result<>();

        if (reservation == null) {
            result.addErrorMessage("Nothing to save");
        }
        if (reservation.getStartDate() == null) {
            result.addErrorMessage("Start Date cannot be empty");
        }

        if (reservation.getEndDate() == null) {
            result.addErrorMessage("End Date cannot be empty");
        }
        if (reservation.getGuest() == null) {
            result.addErrorMessage("Guess cannot be empty");
        }
        if (reservation.getHost() == null) {
            result.addErrorMessage("Host cannot be empty");
        }
        return result;
    }

    public Result<Reservation> deleteReservation(Reservation reservation) throws DataException{
        Result<Reservation> results = new Result<>();
        if(!reservationFileRepository.delete(reservation)) {
            results.addErrorMessage("Reservation Wasn't found");
        }
        return results;
    }

    private void validateDate(Reservation reservation, Result<Reservation> result) {

        List<Reservation> reservations = findById(reservation.getHostId());
        reservations.stream()
                .forEach(r -> {
                    if (reservation.getReservationId() != r.getReservationId()) {
                        if ((reservation.getStartDate().isEqual(r.getStartDate()) ||
                                reservation.getStartDate().isAfter(r.getStartDate())) &&
                                (reservation.getStartDate().isBefore(r.getEndDate()))) {
                            result.addErrorMessage("This start date is unavailable");
                        }
                        if ((reservation.getEndDate().isAfter(r.getStartDate())) &&
                                (reservation.getEndDate().isBefore(r.getEndDate()) ||
                                        reservation.getEndDate().isEqual(r.getEndDate()))
                        ) {
                            result.addErrorMessage("This EndDate overlaps with some dates");
                        }

                    }});

            if (reservation.getEndDate().isBefore(reservation.getStartDate())) {
                result.addErrorMessage("End Date cannot be before start date.");
            }
            if (reservation.getStartDate().isEqual(reservation.getEndDate())) {
                result.addErrorMessage("Check in can't be the same day as checkout");
            }
            if ((reservation.getStartDate().isBefore(LocalDate.now())) ||
                    reservation.getEndDate().isBefore(LocalDate.now())) {
                result.addErrorMessage("Reservation Date cannot be in the past");
            }

    }
}




