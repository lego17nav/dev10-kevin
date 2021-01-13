package learn.hotel;

import learn.hotel.data.GuestFileRepository;
import learn.hotel.data.HostFileRepository;
import learn.hotel.data.ReservationFileRepository;
import learn.hotel.domain.GuestService;
import learn.hotel.domain.HostService;
import learn.hotel.domain.ReservationService;
import learn.hotel.ui.ConsoleIO;
import learn.hotel.ui.Controller;
import learn.hotel.ui.View;

public class App {

    public static void main(String[] args) {

        GuestFileRepository guestFileRepository = new GuestFileRepository("./data/guests.csv");
        HostFileRepository hostFileRepository = new HostFileRepository("./data/hosts.csv");
        ReservationFileRepository reservationFileRepository =
                new ReservationFileRepository("./data/reservations");

        GuestService guestService = new GuestService();
        HostService hostService = new HostService(hostFileRepository);
        ReservationService reservationService = new ReservationService(guestFileRepository,
                hostFileRepository,reservationFileRepository);
        ConsoleIO io = new ConsoleIO();
        View view = new View(io);
        Controller controller = new Controller(hostService,guestService,reservationService, view);

        controller.run();

    }
}
