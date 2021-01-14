package learn.hotel.data;

import learn.hotel.data.HostRepository;
import learn.hotel.models.Host;
import learn.hotel.models.States;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HostFileRepository implements HostRepository{

    private static final String header = "id,last_name,email,phone,address,city,state,postal_code,standard_rate" +
            ",weekend_rate";
    private final String filePath;

    public HostFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Host> findAll() {

        ArrayList<Host> results = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine();

            for (String line = reader.readLine();line != null; line = reader.readLine()) {

                String[] fields = line.split("," , -1);

                if(fields.length == 10) {
                    results.add(toObject(fields));
                }

            }

        } catch(IOException ex) {

            //

        }

        return results;
    }

    @Override
    public Host findById(String id) {
        return findAll().stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Host add(Host host) throws DataException {
        List<Host> all = findAll();
        host.setId(java.util.UUID.randomUUID().toString());
        all.add(host);
        return host;
    }

    private void writeAll(List<Host> hosts) throws DataException {
        try(PrintWriter writer = new PrintWriter(filePath)) {

            writer.println(header);

            if (hosts == null) {
                return;
            }

            for (Host host:hosts) {
                writer.println(toFile(host));
            }
        } catch (FileNotFoundException ex) {
            throw new DataException(ex);
        }

    }

    private String toFile(Host host) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
        host.getId(),
        host.getLastName(),
        host.getEmail(),
        host.getPhone(),
        host.getAddress(),
        host.getCity(),
        host.getState().toString(),
        host.getpCode(),
        host.getRegRate().toString(),
        host.getWeekERate().toString());
    }

    private Host toObject(String[] fields) {
        //id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate
        Host host = new Host();
        host.setId(fields[0]);
        host.setLastName(fields[1]);
        host.setEmail(fields[2]);
        host.setPhone(fields[3]);
        host.setAddress(fields[4]);
        host.setCity(fields[5]);
        host.setState(States.valueOf(fields[6]));
        host.setpCode(fields[7]);
        host.setRegRate(new BigDecimal(fields[8]));
        host.setWeekERate(new BigDecimal(fields[9]));

        return host;
    }



}
