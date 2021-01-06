import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Exercise01 {

    // LocalDate
    // ========================
    // Complete the numbered tasks below.
    // Open and execute the accompanying tests to confirm your solution is correct.

    // 1. return today's date
    LocalDate getToday() {

        return LocalDate.now();
    }

    // 2. return December 17, 1903 as a LocalDate
    LocalDate getFirstFlightDate() {
        return LocalDate.of(1903, 12, 17);
    }

    // 3. if parameter is in the future, return null.
    // Otherwise, add 5 days to the parameter and return the result.
    LocalDate makeFutureNullShiftThePast(LocalDate date) {
        return date.isBefore(LocalDate.now())? date.plusDays(5) : null;
    }

    // 4. return the fifth Friday from the parameter date.
    // if the date is Friday, don't count it.
    LocalDate fiveFridaysFromDate(LocalDate date) {

        boolean logic = date.getDayOfWeek().equals(DayOfWeek.FRIDAY) ? true : false;
        ArrayList<LocalDate> fridays = new ArrayList<>();
        for(;date.compareTo(date.plusWeeks(6)) < 0; date = date.plusDays(1)) {
            if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                fridays.add(date);
            }
        }
        return logic ? fridays.get(5) : fridays.get(4);
    }

    // 5. given a date and a count,
    // return a list of the next `fridayCount` Fridays after the date.
    // if the date is Friday, don't include it.
    List<LocalDate> getNextFridays(LocalDate date, int fridayCount) {
        ArrayList<LocalDate> fridays = new ArrayList<>();
        if(date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
            date = date.plusWeeks(1);
        }
        for(;date.compareTo(date.plusWeeks(5)) < 0; date = date.plusDays(1)) {
            if(date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                fridays.add(date);
            }
        }
        return fridays;
    }

    // 6. return the absolute value of the days between two dates.
    // one may be before two, two may be before one, but neither will be null
    int getDaysBetween(LocalDate one, LocalDate two) {

        return Period.between(one,two).getDays();
    }

}
