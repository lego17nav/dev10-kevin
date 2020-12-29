package learn.venus.data;

import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;
import learn.venus.data.DataAccessException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OrbiterFileRepository {

    private final String filePath;

    public OrbiterFileRepository (String filePath) {
        this.filePath = filePath;
    }

    public List<Orbiter> findAll() {
        ArrayList<Orbiter> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine();

            for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",",-1);
                if(fields.length == 4) {
                    Orbiter orbiter = new Orbiter();
                    orbiter.setOrbiterId(Integer.parseInt(fields[0]));
                    orbiter.setName(fields[1]);
                    orbiter.setType(OrbiterType.valueOf(fields[2]));
                    orbiter.setSponsor(fields[3]);
                    result.add(orbiter);
                }
            }

        } catch (IOException ex) {
            //
        }
        return result;
    }

    public Orbiter findById(int orbiterId) {
        for(Orbiter orbiter: findAll()) {
            if(orbiter.getOrbiterId() == orbiterId) {
                return orbiter;
            }
        }
        return null;
    }

    public Orbiter add(Orbiter orbiter) throws DataAccessException {
        List<Orbiter> all = findAll();

        int nextId = 0;
        for(Orbiter o : all) {
            nextId = Math.max(nextId, o.getOrbiterId());
        }
        nextId++;
        orbiter.setOrbiterId(nextId);
        all.add(orbiter);
        writeAll(all);

        return orbiter;
    }

    public boolean update(Orbiter orbiter) {
        return false;
    }

    public boolean deleteById(int orbiterId) {
        return false;
    }

    private void writeAll(List<Orbiter> orbiters) throws DataAccessException {
        try(PrintWriter writer = new PrintWriter(filePath)) {
            writer.println("orbiterId,name,type,sponsor");
            for(Orbiter o:orbiters) {

            }
        } catch(IOException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }

    private String serialize(Orbiter orbiter) {
        return String.format("%s,%s,%s,%s",
                orbiter.getOrbiterId(),
                orbiter.getName(),
                orbiter.getType(),
                orbiter.getSponsor());
    }
}
