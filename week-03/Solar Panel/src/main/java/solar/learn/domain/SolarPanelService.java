package solar.learn.domain;
import solar.learn.repository.SolarRepository;
import solar.learn.model.SolarPanel;
import solar.learn.model.SolarPanelResults;
import solar.learn.repository.DataAccessException;

import java.util.List;
import java.time.LocalDate;

public class SolarPanelService {

    private final int currentYear = LocalDate.now().getYear();
    private final SolarRepository repository;

    public SolarPanelService(SolarRepository solarRepository) {

        this.repository = solarRepository;

    }
    public List<SolarPanel> findAll() throws DataAccessException {
        List<SolarPanel> panels = repository.findAll();
        return panels;
    }

    public SolarPanelResults add(SolarPanel solarPanel) throws DataAccessException {
        SolarPanelResults results = validate(solarPanel);

        if (results.isSuccess()) {
            solarPanel = repository.add(solarPanel);
            results.setSolarPanel(solarPanel);
        }
        return results;
    }

    public SolarPanelResults update(SolarPanel solarPanel) throws DataAccessException{
        SolarPanelResults results = validate(solarPanel);

        if(results.isSuccess()) {
            if (repository.update(solarPanel)) {
                results.setSolarPanel(solarPanel);
            } else {
                String message = String.format("Solar Panel: %s was not found", solarPanel.getUniqueKey());
            }
        }
        return results;
    }

    public SolarPanelResults deleteByKey(String key) throws DataAccessException {
        SolarPanelResults result = new SolarPanelResults();
        if (!repository.delete(key)) {
            String message = String.format("Key ID: %s was not found", key);
            result.addErrorMessage(message);
        }
        return result;
    }

    private boolean isNotUnique(SolarPanel solarPanel) throws DataAccessException {
        List<SolarPanel> all = findAll();
        for(SolarPanel sp : all) {
            if(sp.getUniqueKey().equalsIgnoreCase(solarPanel.getUniqueKey())) {
                return true;
            }
        }
        return false;
    }

    private SolarPanelResults validate(SolarPanel solarPanel) throws DataAccessException {

        SolarPanelResults result = new SolarPanelResults();
        if (solarPanel == null) {
            result.addErrorMessage("Solar Panel cannot be null");
        }

        if (solarPanel.getCol() == 0 || solarPanel.getCol() > 250) {
            result.addErrorMessage("Column can be below 0 or over 250");
        }

        if (solarPanel.getRow() == 0 || solarPanel.getRow() > 250) {
            result.addErrorMessage("Row can't be below 0 or over 250");
        }

        if (solarPanel.getYearInstalled() < 1884 || solarPanel.getYearInstalled() > currentYear) {
            result.addErrorMessage("Year Installed doesn't make sense please double check");
        }

        if (solarPanel.getSection() == null) {
            result.addErrorMessage("Section can't be empty");
        }
        if (solarPanel.getIsTracking() != true && solarPanel.getIsTracking() != false) {
            result.addErrorMessage("Must have tracking identifier");
        }
        if (isNotUnique(solarPanel)) {
            result.addErrorMessage("Must be unique");
        }

        return result;

    }
}
