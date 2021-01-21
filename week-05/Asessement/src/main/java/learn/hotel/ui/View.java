package learn.hotel.ui;

import learn.hotel.models.Guest;
import learn.hotel.models.Host;
import learn.hotel.models.Reservation;
import learn.hotel.models.States;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class View {

    private final ConsoleIO io;


    public View(ConsoleIO io) {
        this.io = io;
    }

    public MainMenuOption selectMainMenuOption() {
        displayHeader("Main Menu");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (MainMenuOption option : MainMenuOption.values()) {
            if (!option.isHidden()) {
                io.printf("%s. %s%n", option.getValue(), option.getMessage());
            }
            min = Math.min(min, option.getValue());
            max = Math.max(max, option.getValue());
        }

        String message = String.format("Select [%s-%s]: ", min, max);
        return MainMenuOption.fromValue(io.readInt(message, min, max));
    }

    public void displayHeader(String message) {
        io.println("");
        io.println(message);
        io.println("=".repeat(message.length()));
    }

    public void displayException(Exception ex) {
        displayHeader("A critical error occurred:");
        io.println(ex.getMessage());
    }

    public void pressEnter() {
        io.enterContinue();
    }

    public Host chooseHost(List<Host> hosts) {

        if (hosts.size() == 0) {
            io.println("No Hosts Found");
            return null;
        }
        int index = 1;
        for (Host host : hosts.stream().collect(Collectors.toList())) {
            io.printf("%s: %s, %s, %s, %s%n", index++, host.getLastName(), host.getEmail(),
                    host.getPhone(), host.getAddress());
        }
        index--;

        io.println("0: Exit");
        String message = String.format("Select a Host by their index [0-%s]: ", index);

        index = io.readInt(message, 0, index);
        if (index <= 0) {
            return null;
        }
        return hosts.get(index - 1);
    }

    public Guest chooseGuest(List<Guest> guests) {

        if (guests.size() == 0) {
            io.println("No Hosts Found");
            return null;
        }
        int index = 1;
        for (Guest guest : guests.stream().collect(Collectors.toList())) {
            io.printf("%s: %s, %s, %s, %s%n", index++, guest.getLastName(), guest.getFirstName(),
                    guest.getPhone(), guest.getEmail());
        }
        index--;

        io.println("0: Exit");
        String message = String.format("Select a Guest by their index [0-%s]: ", index);

        index = io.readInt(message, 0, index);
        if (index <= 0) {
            return null;
        }
        return guests.get(index - 1);
    }

    public Reservation chooseReservation(List<Reservation> reservations, List<Guest> guests) {
        displayHeader("Select a reservation");
        Map<Integer, Guest> mapGuest = guests.stream()
                .collect(Collectors.toMap(g -> g.getGuestId(), g -> g));

        if (reservations.size() == 0) {
            io.println("No Reservations found");
            return null;
        }
        int index = 1;
        for (Reservation reservation : reservations.stream().collect(Collectors.toList())) {
            io.printf("%s: %s, %s, %s - %s%n", index++, mapGuest.get(reservation.getGuestId()).getFirstName(),
                    mapGuest.get(reservation.getGuestId()).getLastName(),
                    reservation.getStartDate(),
                    reservation.getEndDate());
        }
        index--;

        io.println("0: Exit");
        String message = String.format("Select a reservation by their index [0-%s]: ", index);

        index = io.readInt(message, 0, index);
        if (index <= 0) {
            return null;
        }
        return reservations.get(index - 1);
    }

    public void displayHosts(List<Host> hosts) {

        if (hosts.size() == 0) {
            io.println("No hosts Found");
        }
        //last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate
        for (Host host : hosts) {
            io.printf("%s,%s,%s,%s,%s%n", host.getLastName(), host.getEmail(),
                    host.getPhone(), host.getAddress(), host.getCity());
        }
    }

    public Reservation updateReservation(Reservation reservation) {
        while(true) {
            Reservation reservationUpdated = reservation;
            displayHeader("Updating the reservation");
            LocalDate updateStartDate = io.readLocalDateUpdate("Start Date: " + reservation.getStartDate().toString() + ":");
            LocalDate updateEndDate = io.readLocalDateUpdate("End Date: " + reservation.getEndDate().toString() + ":");
            if(updateStartDate != null) {
                reservationUpdated.setStartDate(updateStartDate);
            }
            if(updateEndDate != null) {
                reservationUpdated.setEndDate(updateEndDate);
            }
            io.printf("New reservation : %s to %s for $%s ", reservation.getStartDate(),
                    reservation.getEndDate(),reservation.getValue());
            String confirm = io.readRequiredString("Update reservation? [Y/N]: ");
            if(confirm.equalsIgnoreCase("Y")) {
                return reservationUpdated;
            }
            return reservation;

        }
    }


    public void displayGuests(List<Guest> guests) {

        if (guests.size() == 0) {
            io.println("No hosts Found");
        }
        //guest_id,first_name,last_name,email,phone,state
        for (Guest guest : guests) {
            io.printf("%s,%s,%s,%s,%s%n", guest.getGuestId(), guest.getFirstName(), guest.getLastName()
                    , guest.getEmail(), guest.getPhone());
        }
    }

    public void displayReservations(List<Reservation> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            io.println("No Reservations found.");
            return;
        }

        for (Reservation reservation : reservations) {
            io.printf("Name: %s, Reservation ID: %d, From %s to %s, Guest ID : %d, Total: $%.2f%n",
                    reservation.getGuest().getLastName(),
                    reservation.getReservationId(), reservation.getStartDate(), reservation.getEndDate(),
                    reservation.getGuestId(), reservation.getTotalPrice());
        }
    }

    public boolean cancelReservation(Reservation reservation) {
        io.printf("Cancel the following reservation: %n" +
                "%s %s : From %s to %s%n",
                reservation.getGuest().getFirstName(),
                reservation.getGuest().getLastName(),
                reservation.getStartDate(),
                reservation.getEndDate());
        String confirm = io.readRequiredString("Cancel the following reservation? [Y/N]:");
        return confirm.equals("Y");
    }

    public Reservation createReservation(Host host, Guest guest) {
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setHostId(host.getId());
        reservation.setGuestId(guest.getGuestId());
        reservation.setStartDate(io.readLocalDate("Reservation Start Date [yyyy-MM-dd]:"));
        reservation.setEndDate(io.readLocalDate("Reservation End Date [yyyy-MM-dd]:"));
        io.println("Make the following Reservation? [Y/N]");
        String confirmation = io.readRequiredString("From: " + reservation.getStartDate()
        + " To: " + reservation.getEndDate() + " $" + reservation.getValue() + "\n");
        return confirmation.equalsIgnoreCase("y") ? reservation : null;
    }

    public String getHostLastName() {
        return io.readRequiredString("Host last name starts with: ");
    }

    public String getGuestLastName() {
        return io.readRequiredString("Guest Last name starts with:");
    }

    public States getState() {
        displayHeader("State Category");
        int index = 1;
        for (States state : States.values()) {
            io.printf("%s: %s%n", index++, state);
        }
        index--;
        String message = String.format("Select a state [1-%s]:", index);
        return States.values()[io.readInt(message, 1, index) - 1];
    }

    public void displayStatus(boolean success, String message) {
        displayStatus(success, List.of(message));
    }

    public void displayStatus(boolean success, List<String> messages) {
        displayHeader(success ? "Success" : "Error");
        for (String message : messages) {
            io.println(message);
        }
    }

    public void displayConfirmation(String prompt, String name) {
        io.printf("%s : %s%n",prompt,name);
    }
}
