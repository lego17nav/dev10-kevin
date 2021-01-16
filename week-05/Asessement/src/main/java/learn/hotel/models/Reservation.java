package learn.hotel.models;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Reservation {

    private String hostId;
    private Host host;
    private Guest guest;
    private int reservationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int guestId;
    private BigDecimal totalPrice;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int id) {
        this.reservationId = id;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {

        this.totalPrice = totalPrice;

    }

    public BigDecimal getValue() {

        LocalDate calcStartDate = startDate;
        LocalDate calcEndDate = endDate;
        BigDecimal weekdayRate = host.getRegRate();
        BigDecimal weekEndRate = host.getWeekERate();
        BigDecimal finalPrice = new BigDecimal("0");

        for(LocalDate currentDate = calcStartDate; currentDate.isBefore(calcEndDate);
            currentDate = currentDate.plusDays(1)) {
            if(currentDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
                    currentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                finalPrice = finalPrice.add(weekEndRate);
            } else {
                finalPrice = finalPrice.add(weekdayRate);
            }
        }
        return finalPrice;
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
