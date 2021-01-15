package learn.hotel.ui;

import learn.hotel.domain.GuestService;
import learn.hotel.domain.HostService;
import learn.hotel.domain.ReservationService;
import learn.hotel.data.DataException;
import learn.hotel.domain.Result;
import learn.hotel.models.Guest;
import learn.hotel.models.Host;
import learn.hotel.models.Reservation;

import java.util.List;

public class Controller {

    private final HostService hostService;
    private final GuestService guestService;
    private final ReservationService reservationService;
    private final View view;

    public Controller(HostService hostService, GuestService guestService, ReservationService reservationService,
                      View view) {
        this.hostService = hostService;
        this.guestService = guestService;
        this.reservationService = reservationService;
        this.view = view;
    }

    public void run() {
        view.displayHeader("Welcome to Sustainable Foraging");
        try {
            runAppLoop();
        } catch (DataException ex) {
            view.displayException(ex);
        }
        view.displayHeader("Goodbye.");
    }

    private void runAppLoop() throws DataException {
        MainMenuOption option;
        do {
            option = view.selectMainMenuOption();
            switch (option) {
                case VIEW_RESERVATION_BY_HOST:
                    viewByHost();
                    break;
                case MAKE_RESERVATION:
                    addReservation();
                    break;
                case EDIT_RESERVATION:
                    //addForage();
                    break;
                case CANCEL_RESERVATION:
                    //addForager();
                    break;
            }

        } while (option != MainMenuOption.EXIT);
    }

    private void viewByHost() {
        Host host = getHost();
        List<Reservation> reservations = reservationService.findById(host.getId());
        view.displayReservations(reservations);
    }

    private Host getHost() {
        String hostLastNamePrefix = view.getHostLastName();
        List<Host> hosts = hostService.findByLastName(hostLastNamePrefix);
        return view.chooseHost(hosts);
    }

    private Guest getGuest() {
        String guestLasNamePrefix = view.getGuestLastName();
        List<Guest> guests = guestService.findByLastName(guestLasNamePrefix);
        return view.chooseGuest(guests);
    }

    private void addReservation() throws DataException {
        view.displayHeader(MainMenuOption.MAKE_RESERVATION.getMessage());
        Host host = getHost();
        Guest guest = getGuest();
        view.displayConfirmation("Host is%n",host.getLastName());
        view.displayConfirmation("Guest Name is%n", guest.getLastName());
        List<Reservation> reservations = reservationService.findById(host.getId());
        view.displayReservations(reservations);
        Reservation reservation = view.createReservation(host, guest);
        Result<Reservation> result = reservationService.add(reservation);
        if(!result.isSuccess()) {
            view.displayStatus(false,result.getErrorMessages());
        } else {
            String successMessage = String.format("Reservation for %s has been made.",
                    result.getPayload().getGuest().getLastName());
            view.displayStatus(true, successMessage);
        }
    }
}