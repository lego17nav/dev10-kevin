package learn.hotel.data;

import learn.hotel.data.GuestRepository;
import learn.hotel.models.Guest;
import learn.hotel.models.States;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuestFileRepository implements GuestRepository {

    private static final String HEADER = "guest_id,first_name,last_name,email,phone,state";
    private final String filePath;

    public GuestFileRepository(String filePath) {this.filePath = filePath;}


    @Override
    public Guest findByID(int id) {
        return null;
    }

    @Override
    public List<Guest> findAll() {
        ArrayList<Guest> all = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine();

            for(String line = reader.readLine();line != null; line = reader.readLine()) {

                String[] fields = line.split(",",-1);
                if(fields.length == 6) {
                    all.add(toObject(fields));
                }
            }
        } catch(IOException ex) {

        }
        return all;
    }

    @Override
    public Guest add(Guest guest) throws DataException {
        return null;
    }

    private Guest toObject(String[] fields) {
        //guest_id,first_name,last_name,email,phone,state
        Guest guest = new Guest();
        guest.setGuestId(Integer.parseInt(fields[0]));
        guest.setFirstName(fields[1]);
        guest.setLastName(fields[2]);
        guest.setEmail(fields[3]);
        guest.setPhone(fields[4]);
        guest.setState(States.valueOf(fields[5]));

        return guest;
    }

    private String toFile(Guest guest) {
        //guest_id,first_name,last_name,email,phone,state
        return String.format("%s,%s,%s,%s,%s", guest.getFirstName(),guest.getLastName()
        ,guest.getEmail(),guest.getPhone(),guest.getState().toString());
    }
}
