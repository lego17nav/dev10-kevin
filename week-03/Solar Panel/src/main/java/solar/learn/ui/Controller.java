package solar.learn.ui;

import solar.learn.domain.SolarPanelService;
import solar.learn.model.SolarPanel;
import solar.learn.model.SolarPanelResults;
import solar.learn.repository.DataAccessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private final View view;
    private final SolarPanelService service;
    private Scanner console = new Scanner(System.in);

    public Controller(View view, SolarPanelService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        view.displayHeader("Welcome to Solar Panel Menu");
        try {
            runApp();
        } catch (DataAccessException ex) {
            view.displayErrors(List.of(ex.getMessage()));
        }
        view.displayMessage("Goodbye");
    }

    private void runApp() throws DataAccessException {
        for (int option = view.chooseMenuOption(); option > 0; option = view.chooseMenuOption()) {
            switch(option) {
                case 1:
                    viewSolarPanels();
                    break;
                case 2:
                    addSolarPanels();
                    break;
                case 3:
                    updateSolarPanel();
                    break;
                case 4:
                    deleteSolarPanel();
                    break;
            }
        }
    }

    private void viewSolarPanels() throws DataAccessException {
        view.displayHeader("View Solar Panels");
        List<SolarPanel> solarPanels = getSolarPanels();
        view.displaySolarPanels(solarPanels);
    }

    private List<SolarPanel> getSolarPanels() throws DataAccessException {
        System.out.println("Please enter the section you wish to view");

        String input = console.nextLine();
        List<SolarPanel> solarPanels = filterBySection(input);
        return solarPanels;
    }


    private List<SolarPanel> filterBySection(String input) throws DataAccessException {
        List<SolarPanel> solarPanels = service.findAll();
        List<SolarPanel> sectionedPanels = new ArrayList<>();
        for(SolarPanel solarPanel: solarPanels) {
            if(solarPanel.getSection().equalsIgnoreCase(input)) {
                sectionedPanels.add(solarPanel);
            }
        }
        return sectionedPanels;
    }


    private void addSolarPanels() throws DataAccessException {
        SolarPanel solarPanel = view.createSolarPanel();
        SolarPanelResults result = service.add(solarPanel);

        if(result.isSuccess()) {
            view.displayMessage("Solar Panel " + result.getSolarPanel().getSection() + "-" +
                    result.getSolarPanel().getCol() + "-" +
                    result.getSolarPanel().getRow() + " has been installed");
        }
        else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    private void updateSolarPanel() throws DataAccessException {
        view.displayHeader("Update Solar Panel");
        List<SolarPanel> solarPanels = getSolarPanels();
        SolarPanel solarPanel = view.choosePanel(solarPanels);
        if(solarPanel == null) {
            view.displayMessage("Solar panel doesn't exist");
            return;
        }
        solarPanel = view.editSolarPanel(solarPanel);
        SolarPanelResults results = service.update(solarPanel);
        if(results.isSuccess()) {
            view.displayMessage("Solar panel" + results.getSolarPanel().getUniqueKey() + " updated");
        } else {
            view.displayErrors(results.getErrorMessages());
        }
    }

    private void deleteSolarPanel() throws DataAccessException {
        view.displayHeader("Remove Solar Panel");
        List<SolarPanel> solarPanels = getSolarPanels();
        SolarPanel solarPanel = view.choosePanel(solarPanels);

        if (solarPanel != null && service.deleteByKey(solarPanel.getUniqueKey()).isSuccess()) {
            view.displayMessage("Solar Panel " + solarPanel.getUniqueKey() + " Was deleted");
        } else {
            view.displayMessage("Solar Panel was not found!");
        }
    }

}
