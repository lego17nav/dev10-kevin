package solar.learn.repository;

import java.io.*;
import solar.learn.model.Material;
import solar.learn.model.SolarPanel;

import java.util.ArrayList;
import java.util.List;

public class SolarFileRepository implements SolarRepository{

    private final String filepath;
    private final String delimiter = "\\|";

    public SolarFileRepository(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public SolarPanel add(SolarPanel solarPanel) throws DataAccessException {
        List<SolarPanel> all = findAll();
        all.add(solarPanel);
        writeToFile(all);
        return solarPanel;
    }

    @Override
    public SolarPanel findByUniqueKey(String key) throws DataAccessException {
        List<SolarPanel> all = findAll();
        for(SolarPanel solarPanel: all) {
            if(key.equalsIgnoreCase(solarPanel.getUniqueKey())) {
                return solarPanel;
            }
        }
        return null;
    }

    @Override
    public List<SolarPanel> findAll() throws DataAccessException {

        ArrayList<SolarPanel> result = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))) {

            for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(delimiter);

                if(fields.length != 7) {
                    return null;
                }

                SolarPanel solarPanel = new SolarPanel(fields[0],Integer.parseInt(fields[1]),
                        Integer.parseInt(fields[2]),
                        Integer.parseInt(fields[3]),
                        Material.valueOf(fields[4]),
                        fields[5] == "Yes");
                result.add(solarPanel);
            }

        }  catch (FileNotFoundException e) {

        }  catch (IOException e) {
            throw new DataAccessException("Could not open file path: " + filepath, e);
        }
        return result;
    }

    @Override
    public boolean update(SolarPanel solarPanel) throws DataAccessException {
        List<SolarPanel> all = findAll();

        for(int i = 0; i < all.size(); i++) {

            if(all.get(i).getUniqueKey().equalsIgnoreCase(solarPanel.getUniqueKey())) {

                all.set(i, solarPanel);
                writeToFile(all);
                return true;
            }

        }

        return false;
    }

    @Override
    public boolean delete(String uniqueId) throws DataAccessException {
        List<SolarPanel> all = findAll();

        for(int i = 0; i < all.size(); i++) {
            if (all.get(i).getUniqueKey().equalsIgnoreCase(uniqueId)) {

                all.remove(i);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    private void writeToFile(List<SolarPanel> solarPanels) throws DataAccessException {
        try(PrintWriter writer = new PrintWriter(filepath)) {
            for (SolarPanel solarPanel: solarPanels) {
                writer.println(toString(solarPanel));
            }
        }
        catch(FileNotFoundException e) {
            throw new DataAccessException("Could not find file: " + filepath);
        }
    }

    private String toString(SolarPanel solarPanel) {

        return String.format("%s|%s|%s|%s|%s|%s|%s",solarPanel.getSection(),
                solarPanel.getRow(),
                solarPanel.getCol(),
                solarPanel.getYearInstalled(),
                solarPanel.getMaterial(),
                solarPanel.getIsTracking(),
                solarPanel.getUniqueKey());
    }

}
