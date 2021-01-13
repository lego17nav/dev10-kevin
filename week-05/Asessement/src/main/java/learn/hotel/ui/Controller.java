package learn.hotel.ui;

import learn.hotel.domain.GuestService;
import learn.hotel.domain.HostService;
import learn.hotel.domain.ReservationService;
import learn.hotel.data.DataException;
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
                /*case VIEW_ITEMS:
                    viewItems();
                    break;
                case ADD_FORAGE:
                    addForage();
                    break;
                case ADD_FORAGER:
                    addForager();
                    break;
                case ADD_ITEM:
                    addItem();
                    break;*/

            }

        } while (option != MainMenuOption.EXIT);
    }
    private void viewByHost() {
        Host host = getHost();
        List<Reservation> reservations = reservationService.findById(host.getId());
    }

    private Host getHost() {
        List<Host> hosts = hostService.findAll();
        for(Host host : hosts) {
            System.out.println(host.getLastName());
        }
        return view.chooseHost(hosts);
    }

}