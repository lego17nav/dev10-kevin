package solar.learn.model;

import java.util.ArrayList;
import java.util.List;

public class SolarPanelResults {

    private SolarPanel solarPanel;
    private ArrayList<String> messages = new ArrayList<>();

    public SolarPanel getSolarPanel() {
        return solarPanel;
    }

    public void setSolarPanel(SolarPanel solarPanel) {
        this.solarPanel = solarPanel;
    }

    public boolean isSuccess() {
        return messages.size() == 0;
    }

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message) {
        messages.add(message);
    }

}
