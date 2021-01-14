package learn.hotel.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {

    private String hostId;
    private Host host;
    private Guest guest;
    private int reservationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int guestId;
    private double totalPrice;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int id) {
        this.reservationId = reservationId;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getValue() {
        return null;
    }

    public void setHost(Host host) {
        this.host = host;
    }
    public Host getHost() {return host;}

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    public Guest getGuest(){return guest;}

}
