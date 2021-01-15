package learn.hotel.data;

import learn.hotel.models.Reservation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservationFileRepository implements ReservationRepository{

    private static final String HEADER = "id,start_date,end_date,guest_id,total";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final String directory;

    public ReservationFileRepository(String directory) {
        this.directory = directory;
    }

    @Override
    public List<Reservation> findById(String id) {
        ArrayList<Reservation> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(id)))) {

            reader.readLine();

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",", -1);
                if(fields.length == 5) {
                    result.add(toObject(fields, id));
                }

            }

        } catch(IOException ex) {

        }
        return result;
    }

    @Override
    public Reservation add(Reservation reservation) throws DataException {
        if(reservation == null) {
            return null;
        }
        List<Reservation> all = findById(reservation.getHostId());

        int nextId = all.stream()
                .mapToInt(r -> r.getReservationId())
                .max()
                .orElse(0) + 1;
        reservation.setReservationId(nextId);
        all.add(reservation);
        writeAll(all,reservation.getHostId());

        return reservation;
    }

    @Override
    public boolean update(Reservation reservation) throws DataException {
        return false;
    }

    private String getFilePath(String path) {return Paths.get(directory, path + ".csv").toString();}

    private Reservation toObject(String[] fields, String hostID) {
        //id,start_date,end_date,guest_id,total
        Reservation result = new Reservation();
        result.setReservationId(Integer.parseInt(fields[0]));
        result.setStartDate(LocalDate.parse(fields[1], formatter));
        result.setEndDate(LocalDate.parse(fields[2], formatter));
        result.setGuestId(Integer.parseInt(fields[3]));
        result.setTotalPrice(new BigDecimal(String.valueOf(fields[4])));
        result.setHostId(hostID);

        return result;
    }

    private String toString(Reservation reservation) {
        // id,start_date,end_date,guest_id,total
        return String.format("%s,%s,%s,%s,%s",
                reservation.getReservationId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getGuestId(),
                reservation.getTotalPrice());
    }

    private void writeAll(List<Reservation> reservations,String hostId) throws DataException {

        try (PrintWriter writer = new PrintWriter(getFilePath(hostId))) {

            writer.println(HEADER);

            for(Reservation reservation: reservations) {
                writer.println(toString(reservation));
            }

        } catch (IOException ex) {
            throw new DataException(ex);
        }

    }

}
